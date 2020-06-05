package b3nac.injuredandroid

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class b25lActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b25l)
        FlagsOverview.flagTwoButtonColor = true
        val settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean("flagTwoButtonColor", true).apply()
    }
}