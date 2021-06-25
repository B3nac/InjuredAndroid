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
    
    lateinit var sharedPreferences: SharedPreferences

    var flagOneButtonColor = false
    var flagTwoButtonColor = false
    var flagThreeButtonColor = false
    var flagFourButtonColor = false
    var flagFiveButtonColor = false
    var flagNineButtonColor = false
    var flagTenButtonColor = false
    var flagElevenButtonColor = false
    var flagFourteenButtonColor = false

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
        val flagFifteenButton: Button
        flagFifteenButton = findViewById(R.id.button41)
        val flagSixteenButton: Button
        flagSixteenButton = findViewById(R.id.button43)
        val flagSeventeenButton: Button
        flagSeventeenButton = findViewById(R.id.button46)
        val flagEighteenButton: Button
        flagEighteenButton = findViewById(R.id.button47)

        //Start of flag one
        if (sharedPreferences.getBoolean("flagOneButtonColor", false)) {
            flagOneButtonColor = sharedPreferences.getBoolean("flagOneButtonColor", true)
            flagOneButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagOneButtonColor", true)) {
            flagOneButtonColor = sharedPreferences.getBoolean("flagOneButtonColor", false)
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
        if (sharedPreferences.getBoolean("flagThreeButtonColor", false)) {
            flagThreeButtonColor = sharedPreferences.getBoolean("flagThreeButtonColor", true)
            flagThreeButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagThreeButtonColor", true)) {
            flagThreeButtonColor = sharedPreferences.getBoolean("flagThreeButtonColor", false)
            flagThreeButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagThreeButtonColor) {
            flagThreeButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag four
        if (sharedPreferences.getBoolean("flagFourButtonColor", false)) {
            flagFourButtonColor = sharedPreferences.getBoolean("flagFourButtonColor", true)
            flagFourButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagFourButtonColor", true)) {
            flagFourButtonColor = sharedPreferences.getBoolean("flagFourButtonColor", false)
            flagFourButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagFourButtonColor) {
            flagFourButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag five
        if (sharedPreferences.getBoolean("flagFiveButtonColor", false)) {
            flagFiveButtonColor = sharedPreferences.getBoolean("flagFiveButtonColor", true)
            flagFiveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagFiveButtonColor", true)) {
            flagFiveButtonColor = sharedPreferences.getBoolean("flagFiveButtonColor", false)
            flagFiveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagFiveButtonColor) {
            flagFiveButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag six
        if (sharedPreferences.getBoolean("flagSixButtonColor", false)) {
            flagSixButtonColor = sharedPreferences.getBoolean("flagSixButtonColor", true)
            flagSixButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagSixButtonColor", true)) {
            flagSixButtonColor = sharedPreferences.getBoolean("flagSixButtonColor", false)
            flagSixButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagSixButtonColor) {
            flagSixButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag seven
        if (sharedPreferences.getBoolean("flagSevenButtonColor", false)) {
            flagSevenButtonColor = sharedPreferences.getBoolean("flagSevenButtonColor", true)
            flagSevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagSevenButtonColor", true)) {
            flagSevenButtonColor = sharedPreferences.getBoolean("flagSevenButtonColor", false)
            flagSevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagSevenButtonColor) {
            flagSevenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag eight
        if (sharedPreferences.getBoolean("flagEightButtonColor", false)) {
            flagEightButtonColor = sharedPreferences.getBoolean("flagEightButtonColor", true)
            flagEightButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagEightButtonColor", true)) {
            flagEightButtonColor = sharedPreferences.getBoolean("flagEightButtonColor", false)
            flagEightButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagEightButtonColor) {
            flagEightButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag nine
        if (sharedPreferences.getBoolean("flagNineButtonColor", false)) {
            flagNineButtonColor = sharedPreferences.getBoolean("flagNineButtonColor", true)
            flagNineButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagNineButtonColor", true)) {
            flagNineButtonColor = sharedPreferences.getBoolean("flagNineButtonColor", false)
            flagNineButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagNineButtonColor) {
            flagNineButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag ten
        if (sharedPreferences.getBoolean("flagTenButtonColor", false)) {
            flagTenButtonColor = sharedPreferences.getBoolean("flagTenButtonColor", true)
            flagTenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagTenButtonColor", true)) {
            flagTenButtonColor = sharedPreferences.getBoolean("flagTenButtonColor", false)
            flagTenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagTenButtonColor) {
            flagTenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag eleven
        if (sharedPreferences.getBoolean("flagElevenButtonColor", false)) {
            flagElevenButtonColor = sharedPreferences.getBoolean("flagElevenButtonColor", true)
            flagElevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagElevenButtonColor", true)) {
            flagElevenButtonColor = sharedPreferences.getBoolean("flagElevenButtonColor", false)
            flagElevenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagElevenButtonColor) {
            flagElevenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag twelve
        if (sharedPreferences.getBoolean("flagTwelveButtonColor", false)) {
            flagTwelveButtonColor = sharedPreferences.getBoolean("flagTwelveButtonColor", true)
            flagTwelveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagTwelveButtonColor", true)) {
            flagTwelveButtonColor = sharedPreferences.getBoolean("flagTwelveButtonColor", false)
            flagTwelveButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagTwelveButtonColor) {
            flagTwelveButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag thirteen
        if (sharedPreferences.getBoolean("flagThirteenButtonColor", false)) {
            flagThirteenButtonColor = sharedPreferences.getBoolean("flagThirteenButtonColor", true)
            flagThirteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagThirteenButtonColor", true)) {
            flagThirteenButtonColor = sharedPreferences.getBoolean("flagThirteenButtonColor", false)
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
        if (sharedPreferences.getBoolean("flagFourteenButtonColor", true)) {
            flagFourteenButtonColor = flutterprefs.getBoolean("flagFourteenButtonColor", false)
            flagFourteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (value != "Flag fourteen found!") {
            flagFourteenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag fifteen
        if (sharedPreferences.getBoolean("flagFifteenButtonColor", false)) {
            flagFifteenButtonColor = sharedPreferences.getBoolean("flagFifteenButtonColor", true)
            flagFifteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagFifteenButtonColor", true)) {
            flagFifteenButtonColor = sharedPreferences.getBoolean("flagFifteenButtonColor", false)
            flagFifteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagFifteenButtonColor) {
            flagFifteenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag sixteen
        if (sharedPreferences.getBoolean("flagSixteenButtonColor", false)) {
            flagSixteenButtonColor = sharedPreferences.getBoolean("flagSixteenButtonColor", true)
            flagSixteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagSixteenButtonColor", true)) {
            flagSixteenButtonColor = sharedPreferences.getBoolean("flagSixteenButtonColor", false)
            flagSixteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagSixteenButtonColor) {
            flagSixteenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag seventeen
        if (sharedPreferences.getBoolean("flagSeventeenButtonColor", false)) {
            flagSeventeenButtonColor = sharedPreferences.getBoolean("flagSeventeenButtonColor", true)
            flagSeventeenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagSeventeenButtonColor", true)) {
            flagSeventeenButtonColor = sharedPreferences.getBoolean("flagSeventeenButtonColor", false)
            flagSeventeenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagSeventeenButtonColor) {
            flagSeventeenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
        //Start of flag eighteen
        if (sharedPreferences.getBoolean("flagEighteenButtonColor", false)) {
            flagEighteenButtonColor = sharedPreferences.getBoolean("flagEighteenButtonColor", true)
            flagEighteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (sharedPreferences.getBoolean("flagEighteenButtonColor", true)) {
            flagEighteenButtonColor = sharedPreferences.getBoolean("flagEighteenButtonColor", false)
            flagEighteenButton.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        }
        if (!flagEighteenButtonColor) {
            flagEighteenButton.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        }
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

    companion object {

        @kotlin.jvm.JvmField
        var flagSixButtonColor = false

        @kotlin.jvm.JvmField
        var flagSevenButtonColor = false

        @kotlin.jvm.JvmField
        var flagEightButtonColor = false

        @kotlin.jvm.JvmField
        var flagNineButtonColor = false

        @kotlin.jvm.JvmField
        var flagTwelveButtonColor = false

        @kotlin.jvm.JvmField
        var flagThirteenButtonColor = false

        @kotlin.jvm.JvmField
        var flagFifteenButtonColor = false

        @kotlin.jvm.JvmField
        var flagSixteenButtonColor = false

        @kotlin.jvm.JvmField
        var flagSeventeenButtonColor = false

        @kotlin.jvm.JvmField
        var flagEighteenButtonColor = false
    }

}