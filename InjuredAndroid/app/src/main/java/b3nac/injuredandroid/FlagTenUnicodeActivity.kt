package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Base64
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
import java.nio.charset.StandardCharsets
import java.util.*

class FlagTenUnicodeActivity : AppCompatActivity() {
    internal var click = 0
    val directory = "dW5pY29kZS8="
    var decodedDirectory = Base64.decode(directory, Base64.DEFAULT)
    val refDirectory = String(decodedDirectory, StandardCharsets.UTF_8)
    internal var database = FirebaseDatabase.getInstance().reference
    internal var childRef = database.child(refDirectory)
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_ten_unicode)
        SecureSharedPrefs.setContext(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        mAuth = FirebaseAuth.getInstance()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            if (click == 0) {
                Snackbar.make(view, "Find the email address.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 1) {
                Snackbar.make(view, "Cause a unicode collision.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
    }

    fun submitFlag(@Suppress("UNUSED_PARAMETER")view: View) {

        if (mAuth?.getCurrentUser() != null) {

            val editText = findViewById<EditText>(R.id.editText4)
            val post = editText.text.toString()

            childRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.value as String?
                        when {
                            post == value -> Toast.makeText(this@FlagTenUnicodeActivity, "No cheating. :]",
                                    Toast.LENGTH_SHORT).show()
                            post.toUpperCase(Locale.ROOT) == value!!.toUpperCase(Locale.ROOT) -> correctFlag()
                            else -> Toast.makeText(this@FlagTenUnicodeActivity, "Try again! :D",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@FlagTenUnicodeActivity, "User not logged in!",
                            Toast.LENGTH_SHORT).show()
                }
            })

        } else {
            Toast.makeText(this@FlagTenUnicodeActivity, "Not authenticated!",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun correctFlag() {
        val intent = Intent(this, FlagOneSuccess::class.java)
        FlagsOverview().flagTenButtonColor = true
        SecureSharedPrefs().editBoolean(this, "flagTenButtonColor", true)
        startActivity(intent)
    }
}
