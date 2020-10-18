package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.nio.charset.StandardCharsets

class FlagNineFirebaseActivity : AppCompatActivity() {

    var click = 0
    val directory = "ZmxhZ3Mv"
    var decodedDirectory = Base64.decode(directory, Base64.DEFAULT)
    val refDirectory = String(decodedDirectory, StandardCharsets.UTF_8)
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child(refDirectory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_nine_firebase)
        SecureSharedPrefs.setContext(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            if (click == 0) {
                Snackbar.make(view!!, "Use the .json trick with database url", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                //Figure out how to login anonymously on click
                click = click + 1
            } else if (click == 1) {
                Snackbar.make(view!!, "Filenames.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = click + 1
            } else if (click == 2) {
                Snackbar.make(view!!, "Encoding.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText2 = findViewById<EditText>(R.id.editText2)
        val post = editText2.text.toString()
        val decodedPost = Base64.decode(post, Base64.DEFAULT)
        val decoded = String(decodedPost, StandardCharsets.UTF_8)
        childRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                if (decoded == value) {
                    FlagsOverview.flagNineButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagNineButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@FlagNineFirebaseActivity, "Try again! :D",
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
        private const val TAG = "FirebaseActivity"
    }
}