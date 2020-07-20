package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FlagEightLoginActivity : AppCompatActivity() {
    var click = 0
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child("/aws")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_eight_login)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val mAuth: FirebaseAuth
        mAuth = FirebaseAuth.getInstance()
        mAuth.signInAnonymously()
                .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInAnonymously:success")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                        Toast.makeText(this@FlagEightLoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            if (click == 0) {
                Snackbar.make(view!!, "AWS CLI.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 1) {
                Snackbar.make(view!!, "AWS profiles and credentials.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText2 = findViewById<EditText>(R.id.editText9)
        val post = editText2.text.toString()
        childRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                if (post == value) {
                    FlagsOverview.flagEightButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagEightButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@FlagEightLoginActivity, "Try again! :D",
                            Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException())
            }
        })
    }

    private fun correctFlag() {
        val intent = Intent(this, FlagOneSuccess::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "FlagEightLoginActivity"
    }
}