package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FlagOneLoginActivity : AppCompatActivity() {
    var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_one_login)
        SecureSharedPrefs.setContext(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            if (click == 0) {
                Snackbar.make(view, "The flag is right under your nose.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 1) {
                Snackbar.make(view, "The flag is also under the GUI.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText2 = findViewById<EditText>(R.id.editText2)
        val post = editText2.text.toString()
        if (post == "F1ag_0n3") {
            val intent = Intent(this, FlagOneSuccess::class.java)
            FlagsOverview().flagOneButtonColor = true
            SecureSharedPrefs().editBoolean(this, "flagOneButtonColor", true)
            startActivity(intent)
        }
    }
}