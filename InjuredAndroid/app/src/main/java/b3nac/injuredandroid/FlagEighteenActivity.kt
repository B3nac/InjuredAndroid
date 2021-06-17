package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FlagEighteenActivity : AppCompatActivity() {

    var click = 0
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child("/fileprovider")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_eighteen)
        setSupportActionBar(findViewById(R.id.toolbar))
        SecureSharedPrefs.setContext(this)
        setResult(-1, intent)
        anon()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            if (click == 0) {
                Snackbar.make(view!!, "Use another activity to move the file!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                //Figure out how to login anonymously on click
                click++
            } else if (click == 1) {
                Snackbar.make(view!!, "Use another Android app to access internal directories.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 2) {
                Snackbar.make(view!!, "Uri permissions.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 3) {
                Snackbar.make(view!!, "Hashes.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
        }

        }
    }

    fun submitFlag(view: View?) {
        // MD5
        val editText = findViewById<EditText>(R.id.enterFlag)
        val post = editText.text.toString()
        childRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                if (post == value) {
                    FlagsOverview.flagEighteenButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagEighteenButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@FlagEighteenActivity, "Try again! :D",
                            Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Access denied", "onCancelled", databaseError.toException())
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