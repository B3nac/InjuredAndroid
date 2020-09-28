package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FlagSixLoginActivity : AppCompatActivity() {
    var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_six_login)
        SecureSharedPrefs.setContext(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            if (click == 0) {
                Snackbar.make(view!!, "Keys.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 1) {
                Snackbar.make(view!!, "Classes.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText3 = findViewById<EditText>(R.id.editText3)
        val post = editText3.text.toString()
        if (post == VGV4dEVuY3J5cHRpb25Ud28.decrypt("k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ==")) {
            val intent = Intent(this, FlagOneSuccess::class.java)
            FlagsOverview.flagSixButtonColor = true
            val secure = SecureSharedPrefs()
            secure.editBoolean(this, "flagSixButtonColor", true)
            startActivity(intent)
        }
    }
}