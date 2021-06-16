package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    var click = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent()
    }

    fun goToXSSText(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, XSSTextActivity::class.java))
    }

    fun goToFlagOneLogin(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagOneLoginActivity::class.java))
    }

    fun goToFlagTwoBypass(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagTwoActivity::class.java))
    }

    fun goToFlagThreeResources(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagThreeActivity::class.java))
    }

    fun goToFlagFourLogin(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagFourActivity::class.java))
    }

    fun goToFlagFiveReceiver(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, TestBroadcastReceiver::class.java))
    }

    fun goToFlagSixLoginActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagSixLoginActivity::class.java))
    }

    fun goToFlagSevenSqliteActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagSevenSqliteActivity::class.java))
    }

    fun goToFlagEightLoginActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagEightLoginActivity::class.java))
    }

    fun goToFlagNineFirebaseActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagNineFirebaseActivity::class.java))
    }

    fun goToFlagTenUnicodeActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagTenUnicodeActivity::class.java))
    }

    fun goToFlagElevenDeeplinkActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        if (click == 0) {
            Toast.makeText(applicationContext, "Exploit the schema!", Toast.LENGTH_LONG).show()
            click++
        } else if (click == 1) {
            Toast.makeText(applicationContext, "Check the Manifest!", Toast.LENGTH_LONG).show()
            click = 0
        }
    }

    fun goToFlagTwelveProtectedActivity(@Suppress("UNUSED_PARAMETER")view: View?) {

        when (click) {
            0 -> {
                Toast.makeText(applicationContext, "Use an exported activity!", Toast.LENGTH_LONG).show()
                click++
            }
            1 -> {
                Toast.makeText(applicationContext, "A PoC app is needed!", Toast.LENGTH_LONG).show()
                click++
            }
            2 -> {
                Toast.makeText(applicationContext, "Check my Youtube :)", Toast.LENGTH_LONG).show()
                click = 0
            }
        }
    }

    fun goToFlagThirteenActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, RCEActivity::class.java))
    }

    fun goToFlagFourteenActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(
                FlutterActivity
                        .withNewEngine()
                        .initialRoute("splashRoute")
                        .build(this)
        )
    }

    fun goToFlagFifteenActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, AssemblyActivity::class.java))
    }

    fun goToFlagSixteenActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, CSPBypassActivity::class.java))
    }

    fun goToFlagSeventeenActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagSeventeenActivity::class.java))
    }

    fun goToFlagEighteenActivity(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagEighteenActivity::class.java))
    }

    fun goToFlagFlagsOverview(@Suppress("UNUSED_PARAMETER")view: View?) {
        startActivity(Intent(this, FlagsOverview::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.action_home) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        if (itemId == R.id.action_contact) {
            startActivity(Intent(this, ContactActivity::class.java))
        }
        if (itemId == R.id.action_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        return true
    }

    private fun handleIntent() {
        val data = intent.data
        if (data == null) return

        if (data.scheme == "flag11") {
            startActivity(Intent(this, DeepLinkActivity::class.java))
        }

        if (data.scheme == "flag13") {
            startActivity(Intent(this, RCEActivity::class.java))
        }

    }

}