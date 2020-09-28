# InjuredAndroid - CTF

A vulnerable Android application with ctf examples based on bug bounty findings, exploitation concepts, and pure creativity.

Now available on Google Play! [https://play.google.com/store/apps/details?id=b3nac.injuredandroid](https://play.google.com/store/apps/details?id=b3nac.injuredandroid)

---

### Setup for a physical device

1. Download the latest release injuredandroid.apk from the releases or Google Play.

2. Enable USB debugging on your Android test phone.

3. Connect your phone and your pc with a usb cable.

4. Install via adb if installing from releases. `adb install InjuredAndroid.apk`. Note: You need to use the absolute path to the .apk file or be in the same directory.

### Setup for an Android Emulator using Android Studio

1. Use adb to pull the apk off of your device or install after downloading the latest release apk.

2. Start the emulator from Android Studio (I recommend downloading an emulator with Google APIs so root adb can be enabled).

3. Drag and drop the .apk file on the emulator and InjuredAndroid.apk will install.

---

### Build from source

1. `git clone https://github.com/B3nac/InjuredAndroid.git`

2. Create local.properties for the flutter_module

Example for Windows:


```
sdk.dir=C:\\Users\\B3nac\\AppData\\Local\\Android\\Sdk
flutter.sdk=C:\\Users\\YourUsername\\PathTo\\flutter
```

Example for Linux:

```
sdk.dir=/home/username/Android/Sdk
flutter.sdk=/home/username/flutter
```

3. Set the Flutter path in Android Studio

File -> Settings -> Languages ​​& Frameworks -> Flutter

4. Enable Dart Support in Android Studio

5. Run `flutter pub get` to import the flutter dependencies

6. Download the Android NDK that is required for the Assembly flag.

7. Now you should be able to compile the latest release of InjuredAndroid! 

---

### Tips and CTF Overview

Decompiling the Android app is highly recommended.

 - XSSTEST is just for fun and to raise awareness on how WebViews can be made vulnerable to XSS.

 - The login flags just need the flag submitted.

 - The flags without a submit that demonstrate concepts will automatically register in the "Flags Overview" Activity.

 - The exclamatory buttons on the bottom right will give users up to three tips for each flag.

 Good luck and have fun! :D

 ---

### Spoilers

Looking at the source code of the applications in the InjuredAndroid directory, InjuredAndroid-FlagWalkthroughs.md file, or binary source code in the Binaries directory will spoil some if not all of the ctf challenges.