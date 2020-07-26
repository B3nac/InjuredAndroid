import 'package:flutter/material.dart';
import 'package:flutterxssmodule/run_javascript.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() => runApp(LoginXSS());

const PrimaryColor = const Color(0xFF008577);

class LoginXSS extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    final appTitle = 'Flutter XSS';

    return MaterialApp(
      title: appTitle,
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: PrimaryColor,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text(appTitle),
        ),
        body: MyCustomForm(),
      ),
    );
  }
}

// Create a Form widget.
class MyCustomForm extends StatefulWidget {
  @override
  MyCustomFormState createState() {
    return MyCustomFormState();
  }
}

// Create a corresponding State class.
// This class holds data related to the form.
class MyCustomFormState extends State<MyCustomForm> {
  // Create a global key that uniquely identifies the Form widget
  // and allows validation of the form.
  //
  // Note: This is a GlobalKey<FormState>,
  // not a GlobalKey<MyCustomFormState>.
  final _formKey = GlobalKey<FormState>();
  var usernameKey = GlobalKey<FormFieldState>();

  @override
  Widget build(BuildContext context) {
    // Build a Form widget using the _formKey created above.

    return Form(
        key: _formKey,
        child: Padding(
          padding: EdgeInsets.only(
              left: 25.0, right: 25.0, top: 2.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Flexible(
                child: new Container(
                  padding: new EdgeInsets.only(right: 15.0),
                  child: new Text(
                    'Register a user',
                    overflow: TextOverflow.ellipsis,
                    style: new TextStyle(
                      fontSize: 18.0,
                      fontFamily: 'Roboto',
                      color: new Color(0xFF212121),
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
              Padding(
                  padding: EdgeInsets.only(
                      left: 25.0, right: 25.0, top: 25.0),
                  child: new Row(
                    mainAxisSize: MainAxisSize.max,
                    children: <Widget>[
                      new Column(
                        mainAxisAlignment: MainAxisAlignment.start,
                        mainAxisSize: MainAxisSize.min,
                      ),
                    ],
                  )),
              TextFormField(
                decoration: InputDecoration(
                    focusedBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.greenAccent, width: 5.0),
                    ),
                    enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: PrimaryColor, width: 5.0),
                    ),
                    //border: InputBorder.none,
                    hintText: 'Enter a username.', contentPadding: const EdgeInsets.all(20.0)
                ),
                key: usernameKey,
                validator: (username) {
                  if (username.isEmpty) {
                    return 'Please enter a username.';
                  }
                  storeFlagState() async {
                    SharedPreferences prefs = await SharedPreferences.getInstance();
                    prefs.setString('username', username);
                  }
                  storeFlagState();
                  return null;
                },
              ),
              Padding(
                  padding: EdgeInsets.only(
                      left: 25.0, right: 25.0, top: 25.0),
                  child: new Row(
                    mainAxisSize: MainAxisSize.max,
                    children: <Widget>[
                      new Column(
                        mainAxisAlignment: MainAxisAlignment.start,
                        mainAxisSize: MainAxisSize.min,
                      ),
                    ],
                  )),
              TextFormField(
                decoration: InputDecoration(
                    focusedBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.greenAccent, width: 5.0),
                    ),
                    enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: PrimaryColor, width: 5.0),
                    ),
                    //border: InputBorder.none,
                    hintText: 'Enter a password.', contentPadding: const EdgeInsets.all(20.0)
                ),
                validator: (password) {
                  if (password.isEmpty) {
                    return 'Please enter a password.';
                  }
                  return null;
                },
              ),
              Padding(
                  padding: EdgeInsets.only(
                      left: 25.0, right: 25.0, top: 25.0),
                  child: new Row(
                    mainAxisSize: MainAxisSize.max,
                    children: <Widget>[
                      new Column(
                        mainAxisAlignment: MainAxisAlignment.start,
                        mainAxisSize: MainAxisSize.min,
                      ),
                    ],
                  )),
              Padding(
                padding: EdgeInsets.only(
                    left: 25.0, right: 25.0, top: 2.0),
                child: RaisedButton(
                  onPressed: () {
                    // Validate returns true if the form is valid, or false
                    // otherwise.
                    if (_formKey.currentState.validate()) {
                      // If the form is valid, display a Snackbar.
                      Scaffold.of(context)
                          .showSnackBar(SnackBar(content: Text('Processing Data')));
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => MyHomePage(test: usernameKey.currentState.value,),
                          ));
                    }
                  },
                  child: Text('Sign up'),
                ),
              ),
            ],
          ),
        ));
  }
}
