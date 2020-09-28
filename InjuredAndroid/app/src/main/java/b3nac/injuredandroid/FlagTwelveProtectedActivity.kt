package b3nac.injuredandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class FlagTwelveProtectedActivity : AppCompatActivity() {
    var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flagWebView = WebView(this)
        setContentView(flagWebView)
        SecureSharedPrefs.setContext(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var uri: Uri? = null
        val intentToUri = intent.getStringExtra("totally_secure")
        uri = Uri.parse(intentToUri)
        flagWebView.settings.javaScriptEnabled = true
        flagWebView.webChromeClient = WebChromeClient()

        if (intent == null || !intent.hasExtra("totally_secure")) {
            finish()
            return
        }
        val onlyAcceptThisSchema = "https" == uri.scheme
        if (onlyAcceptThisSchema) {
            FlagsOverview.flagTwelveButtonColor = true
            val secure = SecureSharedPrefs()
            secure.editBoolean(applicationContext, "flagTwelveButtonColor", true)
            correctFlag()
        } else {
            flagWebView.loadData(intent.getStringExtra("totally_secure"), "text/html", "UTF-8")
        }
    }

    private fun correctFlag() {
        val intent = Intent(this, FlagOneSuccess::class.java)
        startActivity(intent)
    }
}