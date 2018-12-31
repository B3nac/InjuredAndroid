package b3nac.injuredandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class TestBroadcastReceiver extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter("com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT");
        this.registerReceiver(new Receiver(), filter);
        send();
    }

public void send() {

    //FlagFiveReceiver f = new FlagFiveReceiver();
    String uri = "blah";
    //FlagFiveReceiver.wtf = 2;

    Intent intent = new Intent(getApplicationContext(), FlagFiveReceiver.class);
    intent.setAction("com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT");
    intent.putExtra("url", uri.toString());
    sendBroadcast(intent);
}

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            String url = arg1.getExtras().getString("url");
            WebView webview =(WebView)findViewById(R.id.Bob);
            webview.loadUrl(url);
        }
    }

}
