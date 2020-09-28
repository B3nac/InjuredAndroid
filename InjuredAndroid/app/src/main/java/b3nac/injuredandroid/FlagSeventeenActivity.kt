package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FlagSeventeenActivity : AppCompatActivity() {

    var click = 0
    val directory = "/sslpinningbypass"
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child(directory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_seventeen)

        SecureSharedPrefs.setContext(this)
        anon()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            when (click) {
                0 -> {
                    Snackbar.make(view!!, "Find the SSL pinning form.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click++
                }
                1 -> {
                    Snackbar.make(view!!, "Use Frida to bypass the SSL pinning method.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click++
                }
                2 -> {
                    Snackbar.make(view!!, "Monitor traffic.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click = 0
                }
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText = findViewById<EditText>(R.id.editText11)
        val post = editText.text.toString()

        childRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                if (post == value) {
                    FlagsOverview.flagSeventeenButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagSeventeenButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@FlagSeventeenActivity, "Try again! :D",
                            Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Database Error", "onCancelled", databaseError.toException())
            }
        })
    }

    private fun correctFlag() {
        val intent = Intent(this, FlagOneSuccess::class.java)
        startActivity(intent)
    }

    private fun anon() {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        mAuth.signInAnonymously()
    }
}