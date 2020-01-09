# InjuredAndroid - CTF

A vulnerable Android application with ctf examples based on bug bounty findings, exploitation concepts, or pure creativity. 

### Setup for a physical device

1. Enable debugging on your test phone.

2. Connect your phone and your pc with a usb cable.

3. Install via adb. `adb install InjuredAndroid.apk`.

### Setup for an Android Emulator using Android Studio

1. Download the apk file.

2. Start the emulator from Android Studio (I recommend downloading an emulator with Google APIs so root adb can be enabled).

3. Drag and drop the .apk file on the emulator and the install will start.

### Tips and CTF Overview

Decompiling the Android app is highly recommended.

 - XSSTEST is just for fun and to raise awareness on how WebViews can be made vulnerable to XSS.

 - The login flags just need the flag submitted.

 - The flags without a submit that demonstrate concepts will automatically register in the "Flags Overview" Activity.

 - There is one flag with a Pentesterlab 1 month gift key. The key is stored in a self destructing note after It's read, do not close the browser tab before copying the url.

