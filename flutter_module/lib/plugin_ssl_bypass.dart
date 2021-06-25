import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:ssl_pinning_plugin/ssl_pinning_plugin.dart';
import 'package:http/http.dart';

void main() => runApp(new FlutterSSLBypass());

const PrimaryColor = const Color(0xFF008577);

class FlutterSSLBypass extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _PiningSslData {
  String serverURL = '';
  Map<String, String> headerHttp = new Map();
  String allowedSHAFingerprint = '';
  int timeout = 0;
  SHA sha;
}

class _MyAppState extends State<FlutterSSLBypass> {
  final GlobalKey<FormState> _formKey = new GlobalKey<FormState>();
  _PiningSslData _data = new _PiningSslData();
  BuildContext scaffoldContext;

  @override
  initState() {
    super.initState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  check(String url, String fingerprint, SHA sha, Map<String, String> headerHttp, int timeout) async {

    List<String> allowedShA1FingerprintList = new List();
    allowedShA1FingerprintList.add(fingerprint);

    try {
      // Platform messages may fail, so we use a try/catch PlatformException.
      String checkMsg = await SslPinningPlugin.check(serverURL: url,
          headerHttp: headerHttp,
          sha: sha,
          allowedSHAFingerprints: allowedShA1FingerprintList,
          timeout: timeout);
      
      if (!mounted)
        return;

      Scaffold.of(scaffoldContext).showSnackBar(
        new SnackBar(
          content: new Text(checkMsg),
          duration: Duration(seconds: 1),
          backgroundColor: Colors.green,
        ),

      );
      _makeGetRequest();
    }catch (e){
      Scaffold.of(scaffoldContext).showSnackBar(
        new SnackBar(
          content: new Text(e.toString()),
          duration: Duration(seconds: 1),
          backgroundColor: Colors.red,
        ),
      );
    }
  }

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

  void submit() {
    // First validate form.
    if (_formKey.currentState.validate()) {
      _formKey.currentState.save(); // Save our form now.

      this.check(_data.serverURL, _data.allowedSHAFingerprint, _data.sha, _data.headerHttp, _data.timeout);

      }
    }

  @override
  Widget build(BuildContext context) {
    this.scaffoldContext = context;
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: PrimaryColor,
      ),
      home: new Scaffold(
          appBar: new AppBar(
            title: new Text('Ssl Pinning Plugin'),
          ),
          body:
          new Builder(builder: (BuildContext context) {
            this.scaffoldContext = context;
            return Container(
                padding: EdgeInsets.all(20.0),
                child: Form(
                  key: this._formKey,
                  child: new ListView(
                    children: <Widget>[
                      TextFormField(
                          keyboardType: TextInputType.url,
                          decoration: InputDecoration(
                              hintText: 'https://b3nac.com',
                              labelText: 'URL'
                          ),
                          validator: (value) {
                            if (value.isEmpty) {
                              return 'Please enter some url';
                            }
                            return null;
                          },
                          onSaved: (String value) {
                            this._data.serverURL = value;
                          }
                      ),
                      DropdownButton(
                        items: [DropdownMenuItem(child: Text(SHA.SHA1.toString()), value: SHA.SHA1,), DropdownMenuItem(child: Text(SHA.SHA256.toString()), value: SHA.SHA256,)],
                        value: _data.sha,
                        isExpanded: true,
                        onChanged: (SHA val){
                          setState(() {
                            this._data.sha = val;
                          });
                        },
                      ),
                      TextFormField(
                          keyboardType: TextInputType.text,
                          decoration: InputDecoration(
                              hintText: 'OO OO OO OO OO OO OO OO OO OO',
                              labelText: 'Fingerprint'
                          ),
                          validator: (value) {
                            if (value.isEmpty) {
                              return 'Please enter some fingerprint';
                            }
                            return null;
                          },
                          onSaved: (String value) {
                            this._data.allowedSHAFingerprint = value;
                          }
                      ),
                      TextFormField(
                          keyboardType: TextInputType.number,
                          initialValue: '60',
                          decoration: InputDecoration(
                              hintText: '60',
                              labelText: 'Timeout'
                          ),
                          validator: (value) {
                            if (value.isEmpty) {
                              return 'Please enter some timeout';
                            }
                            return null;
                          },
                          onSaved: (String value) {
                            this._data.timeout = int.parse(value);
                          }
                      ),
                      Container(
                        child: RaisedButton(
                          child: Text(
                            'Check',
                            style: TextStyle(
                                color: Colors.white
                            ),
                          ),
                          onPressed: () => submit(),
                          color: PrimaryColor,
                        ),
                        margin: EdgeInsets.only(
                            top: 20.0
                        ),
                      )
                    ],
                  ),
                )
            );
          })
      ),
    );
  }
}