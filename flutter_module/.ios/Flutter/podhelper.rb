# Copyright 2014 The Flutter Authors. All rights reserved.
# Use of this source code is governed by a BSD-style license that can be
# found in the LICENSE file.

require 'json'

# Install pods needed to embed Flutter application, Flutter engine, and plugins
# from the host application Podfile.
#
# @example
#   target 'MyApp' do
#     install_all_flutter_pods 'my_flutter'
#   end
# @param [String] flutter_application_path Path of the root directory of the Flutter module.
#                                          Optional, defaults to two levels up from the directory of this script.
#                                          MyApp/my_flutter/.ios/Flutter/../..
def install_all_flutter_pods(flutter_application_path = nil)
  flutter_application_path ||= File.join('..', '..')
  install_flutter_engine_pod
  install_flutter_plugin_pods(flutter_application_path)
  install_flutter_application_pod(flutter_application_path)
end

# Install Flutter engine pod.
#
# @example
#   target 'MyApp' do
#     install_flutter_engine_pod
#   end
def install_flutter_engine_pod
  current_directory = File.expand_path('..', __FILE__)
  engine_dir = File.expand_path('engine', current_directory)
  framework_name = 'Flutter.xcframework'
  copied_engine = File.expand_path(framework_name, engine_dir)
  if !File.exist?(copied_engine)
    # Copy the debug engine to have something to link against if the xcode backend script has not run yet.
    # CocoaPods will not embed the framework on pod install (before any build phases can generate) if the dylib does not exist.
    debug_framework_dir = File.join(flutter_root, 'bin', 'cache', 'artifacts', 'engine', 'ios')
    FileUtils.cp_r(File.join(debug_framework_dir, framework_name), engine_dir)
  end

  # Keep pod path relative so it can be checked into Podfile.lock.
  # Process will be run from project directory.
  engine_pathname = Pathname.new engine_dir
  # defined_in_file is set by CocoaPods and is a Pathname to the Podfile.
  project_directory_pathname = defined_in_file.dirname
  relative = engine_pathname.relative_path_from project_directory_pathname

  pod 'Flutter', :path => relative.to_s, :inhibit_warnings => true
end

# Install Flutter plugin pods.
#
# @example
#   target 'MyApp' do
#     install_flutter_plugin_pods 'my_flutter'
#   end
# @param [String] flutter_application_path Path of the root directory of the Flutter module.
#                                          Optional, defaults to two levels up from the directory of this script.
#                                          MyApp/my_flutter/.ios/Flutter/../..
def install_flutter_plugin_pods(flutter_application_path)
  flutter_application_path ||= File.join('..', '..')

  # Keep pod path relative so it can be checked into Podfile.lock.
  # Process will be run from project directory.
  current_directory_pathname = Pathname.new File.expand_path('..', __FILE__)
  # defined_in_file is set by CocoaPods and is a Pathname to the Podfile.
  project_directory_pathname = defined_in_file.dirname
  relative = current_directory_pathname.relative_path_from project_directory_pathname
  pod 'FlutterPluginRegistrant', :path => File.join(relative, 'FlutterPluginRegistrant'), :inhibit_warnings => true

  symlinks_dir = File.join(relative, '.symlinks')
  FileUtils.mkdir_p(symlinks_dir)

  plugins_file = File.expand_path('.flutter-plugins-dependencies', flutter_application_path)
  plugin_pods = flutter_parse_dependencies_file_for_ios_plugin(plugins_file)
  plugin_pods.each do |plugin_hash|
    plugin_name = plugin_hash['name']
    plugin_path = plugin_hash['path']
    if (plugin_name && plugin_path)
      symlink = File.join(symlinks_dir, plugin_name)
      FileUtils.rm_f(symlink)
      File.symlink(plugin_path, symlink)
      pod plugin_name, :path => File.join(symlink, 'ios'), :inhibit_warnings => true
    end
  end
end

# Install Flutter application pod.
#
# @example
#   target 'MyApp' do
#     install_flutter_application_pod '../flutter_settings_repository'
#   end
# @param [String] flutter_application_path Path of the root directory of the Flutter module.
#                                          Optional, defaults to two levels up from the directory of this script.
#                                          MyApp/my_flutter/.ios/Flutter/../..
def install_flutter_application_pod(flutter_application_path)
  current_directory_pathname = Pathname.new File.expand_path('..', __FILE__)
  app_framework_dir = File.expand_path('App.framework', current_directory_pathname.to_path)
  app_framework_dylib = File.join(app_framework_dir, 'App')
  if !File.exist?(app_framework_dylib)
    # Fake an App.framework to have something to link against if the xcode backend script has not run yet.
    # CocoaPods will not embed the framework on pod install (before any build phases can run) if the dylib does not exist.
    # Create a dummy dylib.
    FileUtils.mkdir_p(app_framework_dir)
    `echo "static const int Moo = 88;" | xcrun clang -x c -dynamiclib -o "#{app_framework_dylib}" -`
  end

  # Keep pod and script phase paths relative so they can be checked into source control.
  # Process will be run from project directory.

  # defined_in_file is set by CocoaPods and is a Pathname to the Podfile.
  project_directory_pathname = defined_in_file.dirname
  relative = current_directory_pathname.relative_path_from project_directory_pathname
  pod 'flutterxssmodule', :path => relative.to_s, :inhibit_warnings => true

  flutter_export_environment_path = File.join('${SRCROOT}', relative, 'flutter_export_environment.sh');
  script_phase :name => 'Run Flutter Build flutterxssmodule Script',
    :script => "set -e\nset -u\nsource \"#{flutter_export_environment_path}\"\n\"$FLUTTER_ROOT\"/packages/flutter_tools/bin/xcode_backend.sh build",
    :execution_position => :before_compile
end

# .flutter-plugins-dependencies format documented at
# https://flutter.dev/go/plugins-list-migration
def flutter_parse_dependencies_file_for_ios_plugin(file)
  file_path = File.expand_path(file)
  return [] unless File.exists? file_path

  dependencies_file = File.read(file)
  dependencies_hash = JSON.parse(dependencies_file)

  # dependencies_hash.dig('plugins', 'ios') not available until Ruby 2.3
  return [] unless dependencies_hash.has_key?('plugins')
  return [] unless dependencies_hash['plugins'].has_key?('ios')
  dependencies_hash['plugins']['ios'] || []
end

def flutter_root
  generated_xcode_build_settings_path = File.expand_path(File.join('..', '..', 'Flutter', 'Generated.xcconfig'), __FILE__)
  unless File.exist?(generated_xcode_build_settings_path)
    raise "#{generated_xcode_build_settings_path} must exist. If you're running pod install manually, make sure flutter pub get is executed first"
  end

  File.foreach(generated_xcode_build_settings_path) do |line|
    matches = line.match(/FLUTTER_ROOT\=(.*)/)
    return matches[1].strip if matches
  end
  # This should never happen...
  raise "FLUTTER_ROOT not found in #{generated_xcode_build_settings_path}. Try deleting Generated.xcconfig, then run flutter pub get"
end
