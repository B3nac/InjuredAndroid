import 'package:flutter/material.dart';
import 'package:flutterxssmodule/plugin_ssl_bypass.dart';
import 'login-xss.dart';
import 'auth-bypass.dart';

void main() => runApp(MyApp());

const PrimaryColor = const Color(0xFF008577);

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    final appTitle = 'Flutter Main';

    return MaterialApp(
      title: appTitle,
      debugShowCheckedModeBanner: false,


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

class MyCustomFormState extends State<MyCustomForm> {

  final _formKey = GlobalKey<FormState>();
  var usernameKey = GlobalKey<FormFieldState>();

  @override
  Widget build(BuildContext context) {

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
                'Flutter based CTF exercises',
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
          Padding(
            padding: EdgeInsets.only(
                left: 25.0, right: 25.0, top: 2.0),
            child: RaisedButton(
              onPressed: () {
                  Scaffold.of(context)
                      .showSnackBar(SnackBar(content: Text('Processing Data')));
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => LoginXSS(),
                      ));
                },
              child: Text('Flutter XSS'),
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
          Padding(
            padding: EdgeInsets.only(
                left: 25.0, right: 25.0, top: 2.0),
            child: RaisedButton(
              onPressed: () {
                  Scaffold.of(context)
                      .showSnackBar(SnackBar(content: Text('Processing Data')));
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => AuthBypass(),
                      ));
                },
              child: Text('Flutter Auth Bypass'),
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
          Padding(
            padding: EdgeInsets.only(
                left: 25.0, right: 25.0, top: 2.0),
            child: RaisedButton(
              onPressed: () {
                Scaffold.of(context)
                    .showSnackBar(SnackBar(content: Text('Processing Data')));
                Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => FlutterSSLBypass(),
                    ));
              },
              child: Text('Flutter SSL Bypass'),
            ),
          ),
        ],
      ),
    ));
  }
}
