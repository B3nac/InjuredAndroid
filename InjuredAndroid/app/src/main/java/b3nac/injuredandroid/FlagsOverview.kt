package b3nac.injuredandroid

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FlagsOverview : AppCompatActivity() {

    private val preferencesName = "b3nac.injuredandroid.encrypted"

    // Step 0: EncryptedSharedPreferences take long to initialize/open, therefor it's better to do it only once and keep an instance
    lateinit var sharedPreferences: SharedPreferences

    var flagTwoButtonColor = false
    override fun onCreate(savedInstanceState: Bundle?) {
        initEncryptedSharedPreferences()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flags_overview)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "If the flag button is green you have captured that flag!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val flagOneButton: Button
        flagOneButton = findViewById(R.id.button10)
        val flagTwoButton: Button
        flagTwoButton = findViewById(R.id.button12)
        val flagThreeButton: Button
        flagThreeButton = findViewById(R.id.button13)
        val flagFourButton: Button
        flagFourButton = findViewById(R.id.button14)
        val flagFiveButton: Button
        flagFiveButton = findViewById(R.id.button15)
        val flagSixButton: Button
        flagSixButton = findViewById(R.id.button16)
        val flagSevenButton: Button
        flagSevenButton = findViewById(R.id.button17)
        val flagEightButton: Button
        flagEightButton = findViewById(R.id.button18)
        val flagNineButton: Button
        flagNineButton = findViewById(R.id.button19)
        val flagTenButton: Button
        flagTenButton = findViewById(R.id.button20)
        val flagElevenButton: Button
        flagElevenButton = findViewById(R.id.button21)
        val flagTwelveButton: Button
        flagTwelveButton = findViewById(R.id.button22)
        val flagThirteenButton: Button
        flagThirteenButton = findViewById(R.id.button37)
        val flagFourteenButton: Button
        flagFourteenButton = findViewById(R.id.button38)

        //Start of flag one
        if (settings.getBoolean("flagOneButtonColor", false)) {
            flagOneButtonColor = settings.getBoolean("flagOneButtonColor", true)
            flagOneButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagOneButtonColor", true)) {
            flagOneButtonColor = settings.getBoolean("flagOneButtonColor", false)
            flagOneButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagOneButtonColor) {
            flagOneButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag two
        if (sharedPreferences.getBoolean("flagTwoButtonColor", false)) {
            flagTwoButtonColor = sharedPreferences.getBoolean("flagTwoButtonColor", true)
            flagTwoButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagTwoButtonColor", true)) {
            flagTwoButtonColor = sharedPreferences.getBoolean("flagTwoButtonColor", false)
            flagTwoButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagTwoButtonColor) {
            flagTwoButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }

        //Start of flag three
        if (settings.getBoolean("flagThreeButtonColor", false)) {
            flagThreeButtonColor = settings.getBoolean("flagThreeButtonColor", true)
            flagThreeButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagThreeButtonColor", true)) {
            flagThreeButtonColor = settings.getBoolean("flagThreeButtonColor", false)
            flagThreeButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagThreeButtonColor) {
            flagThreeButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag four
        if (settings.getBoolean("flagFourButtonColor", false)) {
            flagFourButtonColor = settings.getBoolean("flagFourButtonColor", true)
            flagFourButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagFourButtonColor", true)) {
            flagFourButtonColor = settings.getBoolean("flagFourButtonColor", false)
            flagFourButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagFourButtonColor) {
            flagFourButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag five
        if (settings.getBoolean("flagFiveButtonColor", false)) {
            flagFiveButtonColor = settings.getBoolean("flagFiveButtonColor", true)
            flagFiveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagFiveButtonColor", true)) {
            flagFiveButtonColor = settings.getBoolean("flagFiveButtonColor", false)
            flagFiveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagFiveButtonColor) {
            flagFiveButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag six
        if (settings.getBoolean("flagSixButtonColor", false)) {
            flagSixButtonColor = settings.getBoolean("flagSixButtonColor", true)
            flagSixButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagSixButtonColor", true)) {
            flagSixButtonColor = settings.getBoolean("flagSixButtonColor", false)
            flagSixButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagSixButtonColor) {
            flagSixButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag seven
        if (settings.getBoolean("flagSevenButtonColor", false)) {
            flagSevenButtonColor = settings.getBoolean("flagSevenButtonColor", true)
            flagSevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagSevenButtonColor", true)) {
            flagSevenButtonColor = settings.getBoolean("flagSevenButtonColor", false)
            flagSevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagSevenButtonColor) {
            flagSevenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag eight
        if (settings.getBoolean("flagEightButtonColor", false)) {
            flagEightButtonColor = settings.getBoolean("flagEightButtonColor", true)
            flagEightButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagEightButtonColor", true)) {
            flagEightButtonColor = settings.getBoolean("flagEightButtonColor", false)
            flagEightButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagEightButtonColor) {
            flagEightButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag nine
        if (settings.getBoolean("flagNineButtonColor", false)) {
            flagNineButtonColor = settings.getBoolean("flagNineButtonColor", true)
            flagNineButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagNineButtonColor", true)) {
            flagNineButtonColor = settings.getBoolean("flagNineButtonColor", false)
            flagNineButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagNineButtonColor) {
            flagNineButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag ten
        if (settings.getBoolean("flagTenButtonColor", false)) {
            flagTenButtonColor = settings.getBoolean("flagTenButtonColor", true)
            flagTenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagTenButtonColor", true)) {
            flagTenButtonColor = settings.getBoolean("flagTenButtonColor", false)
            flagTenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagTenButtonColor) {
            flagTenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag eleven
        if (settings.getBoolean("flagElevenButtonColor", false)) {
            flagElevenButtonColor = settings.getBoolean("flagElevenButtonColor", true)
            flagElevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagElevenButtonColor", true)) {
            flagElevenButtonColor = settings.getBoolean("flagElevenButtonColor", false)
            flagElevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagElevenButtonColor) {
            flagElevenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag twelve
        if (settings.getBoolean("flagTwelveButtonColor", false)) {
            flagTwelveButtonColor = settings.getBoolean("flagTwelveButtonColor", true)
            flagTwelveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagTwelveButtonColor", true)) {
            flagTwelveButtonColor = settings.getBoolean("flagTwelveButtonColor", false)
            flagTwelveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagTwelveButtonColor) {
            flagTwelveButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag thirteen
        if (settings.getBoolean("flagThirteenButtonColor", false)) {
            flagThirteenButtonColor = settings.getBoolean("flagThirteenButtonColor", true)
            flagThirteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagThirteenButtonColor", true)) {
            flagThirteenButtonColor = settings.getBoolean("flagThirteenButtonColor", false)
            flagThirteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagThirteenButtonColor) {
            flagThirteenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag fourteen
        val flutterprefs = getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE)
        val value = flutterprefs.getString("flutter.flagFourteenButtonColor", "")
        if (value == "Flag fourteen found!") {
            val editor = settings.edit()
            editor.putBoolean("flagFourteenButtonColor", true).apply()
            flagFourteenButtonColor = true
            flagFourteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (settings.getBoolean("flagFourteenButtonColor", true)) {
            flagFourteenButtonColor = flutterprefs.getBoolean("flagFourteenButtonColor", false)
            flagFourteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (value != "Flag fourteen found!") {
            flagFourteenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
    }

    companion object {
        @JvmField
        var flagOneButtonColor = false
        @JvmField
        var flagThreeButtonColor = false
        @JvmField
        var flagFourButtonColor = false
        @JvmField
        var flagFiveButtonColor = false
        @JvmField
        var flagSixButtonColor = false
        @JvmField
        var flagSevenButtonColor = false
        @JvmField
        var flagEightButtonColor = false
        @JvmField
        var flagNineButtonColor = false
        var flagTenButtonColor = false
        var flagElevenButtonColor = false
        @JvmField
        var flagTwelveButtonColor = false
        @JvmField
        var flagThirteenButtonColor = false
        var flagFourteenButtonColor = false
    }

    private fun initEncryptedSharedPreferences() {

        // Step 1: Create or retrieve the Master Key for encryption/decryption
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        // Step 2: Initialize/open an instance of EncryptedSharedPreferences
        sharedPreferences = EncryptedSharedPreferences.create(
                preferencesName,
                masterKeyAlias,
                applicationContext,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}