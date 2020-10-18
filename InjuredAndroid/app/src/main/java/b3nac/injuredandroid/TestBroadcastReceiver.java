package b3nac.injuredandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

    String uri = "Hi";

    Intent intent = new Intent(getApplicationContext(), FlagFiveReceiver.class);
    intent.setAction("com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT");
    intent.putExtra("url", uri);
    sendBroadcast(intent);
}

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            String url = arg1.getExtras().getString("url");
            WebView webview = findViewById(R.id.Bob);
            webview.loadUrl(url);
        }
    }

}
