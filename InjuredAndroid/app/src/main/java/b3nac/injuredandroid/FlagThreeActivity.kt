package b3nac.injuredandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FlagThreeActivity : AppCompatActivity() {

    var click = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_three)
        SecureSharedPrefs.setContext(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            if (click == 0) {
                Snackbar.make(view, "R stands for resources.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 1) {
                Snackbar.make(view, "Check .xml files.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText2 = findViewById<EditText>(R.id.editText2)
        val post = editText2.text.toString()

        if (post == getString(R.string.cmVzb3VyY2VzX3lv)) {
            val intent = Intent(this, FlagOneSuccess::class.java)
            FlagsOverview().flagThreeButtonColor = true
            SecureSharedPrefs().editBoolean(this, "flagThreeButtonColor", true)
            startActivity(intent)
        }
    }
}