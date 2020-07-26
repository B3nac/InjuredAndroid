package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
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

class AssemblyActivity : AppCompatActivity() {

    var click = 0
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child("/assembly")

    val XORstring = stringFromJNI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assembly)
        SecureSharedPrefs.setContext(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val tv = findViewById<TextView>(R.id.textView10)
        setSupportActionBar(toolbar)
        anon()

        val charset = Charsets.UTF_8
        val bytes = XORstring.toByteArray(charset)

        tv.text = bytes.contentToString()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            when (click) {
                0 -> {
                    Snackbar.make(view!!, "Covert the byte array to a string.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click++
                }
                1 -> {
                    Snackbar.make(view!!, "Disassemble Shared Object file to find the XOR key.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click++
                }
                2 -> {
                    Snackbar.make(view!!, "Reverse the XOR string.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click = 0
                }
            }
        }
    }

    fun submitFlag(view: View?) {

        val editText2 = findViewById<EditText>(R.id.enterFlag)
        val post = editText2.text.toString()
        childRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                if (post == value) {
                    FlagsOverview.flagFifteenButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagFifteenButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@AssemblyActivity, "Try again! :D",
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

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}