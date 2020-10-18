package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FlagFourActivity : AppCompatActivity() {
    var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_four)
        SecureSharedPrefs.setContext(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            if (click == 0) {
                Snackbar.make(view, "Where is bob.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 1) {
                Snackbar.make(view, "Classes and imports.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(view: View?) {

        val editText2 = findViewById<EditText>(R.id.editText2)
        val post = editText2.text.toString()
        val decoder = Decoder()
        val bob = String(decoder.getData())
        if (post == bob) {
            val intent = Intent(this, FlagOneSuccess::class.java)
            FlagsOverview().flagFourButtonColor = true
            SecureSharedPrefs().editBoolean(this, "flagFourButtonColor", true)
            startActivity(intent)
        }
    }
}