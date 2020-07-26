package b3nac.injuredandroid

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class DisplayPostXSS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vulnWebView = WebView(this)
        setContentView(vulnWebView)
        val intent = intent
        val postText = intent.getStringExtra(XSSTextActivity.POST_STRING)
        vulnWebView.settings.javaScriptEnabled = true
        vulnWebView.webChromeClient = WebChromeClient()
        vulnWebView.loadData(postText, "text/html", "UTF-8")
    }
}