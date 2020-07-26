package b3nac.injuredandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class b25lActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b25l)
        SecureSharedPrefs.setContext(this)

        FlagsOverview().flagTwoButtonColor = true

        SecureSharedPrefs().editBoolean(this, "flagTwoButtonColor", true)
    }
}
