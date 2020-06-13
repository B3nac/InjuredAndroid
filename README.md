# InjuredAndroid - CTF

A vulnerable Android application with ctf examples based on bug bounty findings, exploitation concepts, and pure creativity.

### Setup for a physical device

1. Download injuredandroid.apk from Github

2. Enable USB debugging on your Android test phone.

3. Connect your phone and your pc with a usb cable.

4. Install via adb. `adb install InjuredAndroid.apk`. Note: You need to use the absolute path to the .apk file or be in the same directory.

### Setup for an Android Emulator using Android Studio

1. Download the apk file.

2. Start the emulator from Android Studio (I recommend downloading an emulator with Google APIs so root adb can be enabled).

3. Drag and drop the .apk file on the emulator and InjuredAndroid.apk will install.

### Tips and CTF Overview

Decompiling the Android app is highly recommended.

 - XSSTEST is just for fun and to raise awareness on how WebViews can be made vulnerable to XSS.

 - The login flags just need the flag submitted.

 - The flags without a submit that demonstrate concepts will automatically register in the "Flags Overview" Activity.

 - The exclamatory buttons on the bottom right will give users up to three tips for each flag.

 Good luck and have fun! :D

### Spoilers

Looking at the source code of the applications in the InjuredAndroid directory, InjuredAndroid-FlagWalkthroughs.md file, or binary source code in the Binaries directory will spoil some if not all of the ctf challenges.
