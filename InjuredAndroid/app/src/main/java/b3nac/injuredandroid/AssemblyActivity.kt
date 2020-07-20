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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class AssemblyActivity : AppCompatActivity() {

    var click = 0
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child("/assembly")

    companion object {
        init {
            System.loadLibrary("native-lib")
        }

        private const val TAG = "AssemblyActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assembly)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val tv = findViewById<TextView>(R.id.textView10)
        setSupportActionBar(toolbar)

        val XORstring = stringFromJNI()
        val bytes = XORstring.toByteArray()
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
                    FlagsOverview.flagEightButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagEightButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@AssemblyActivity, "Try again! :D",
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

    external fun stringFromJNI(): String
}