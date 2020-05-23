package b3nac.injuredandroid;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class DisplayPostXSS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView vulnWebView = new WebView(this);
        setContentView(vulnWebView);
        Intent intent = getIntent();
        String postText = intent.getStringExtra(XSSTextActivity.POST_STRING);

        vulnWebView.getSettings().setJavaScriptEnabled(true);
        vulnWebView.setWebChromeClient(new WebChromeClient());
        vulnWebView.loadData(postText, "text/html","UTF-8");
    }
}
