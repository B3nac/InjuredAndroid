# InjuredAndroid - CTF

<<<<<<< HEAD
A vulnerable Android application with ctf examples based on bug bounty findings, exploitation concepts, and pure creativity.
=======
A vulnerable Android application with ctf examples based on bug bounty findings, exploitation concepts, and pure creativity. 
>>>>>>> 81d562ad012f14d9005c8d1c87d038bd9096c130

### Setup for a physical device

1. Download injuredandroid.apk from Github

1. Enable USB debugging on your Android test phone.

2. Connect your phone and your pc with a usb cable.

3. Install via adb. `adb install injuredandroid.apk`. Note: You need to use the absolute path to the .apk file or be in the same directory.

### Setup for an Android Emulator using Android Studio

1. Download the apk file.

2. Start the emulator from Android Studio (I recommend downloading an emulator with Google APIs so root adb can be enabled).

<<<<<<< HEAD
3. Drag and drop the .apk file on the emulator and InjuredAndroid.apk will install.
=======
3. Drag and drop the .apk file on the emulator and injuredandroid.apk will install.
>>>>>>> 81d562ad012f14d9005c8d1c87d038bd9096c130

### Tips and CTF Overview

Decompiling the Android app is highly recommended.

 - XSSTEST is just for fun and to raise awareness on how WebViews can be made vulnerable to XSS.

 - The login flags just need the flag submitted.

 - The flags without a submit that demonstrate concepts will automatically register in the "Flags Overview" Activity.

<<<<<<< HEAD
 - The exclamatory buttons on the bottom right will give users up to three tips for each flag.

 Good luck and have fun! :D
=======
 - The last two flags don't register because there currently isn't a remote verification method (I plan to change this in a future update). This was done to prevent using previous flag methods to skip the exploitation techniques.

 - There is one flag with a Pentesterlab 1 month gift key. The key is stored in a self destructing note after It's read, do not close the browser tab before copying the url.
>>>>>>> 81d562ad012f14d9005c8d1c87d038bd9096c130

 - The exclamatory buttons on the bottom right will give users up to three tips for each flag.

 Good luck and have fun! :D

