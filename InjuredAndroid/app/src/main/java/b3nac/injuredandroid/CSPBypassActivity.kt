package b3nac.injuredandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.content_c_s_p_bypass.*


class CSPBypassActivity : AppCompatActivity() {

    var click = 0
    val directory = "/csp"
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child(directory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_s_p_bypass)
        val editText = findViewById<EditText>(R.id.editText10)
        val button = findViewById<Button>(R.id.button42)
        editText.visibility = View.INVISIBLE
        button.visibility = View.INVISIBLE
        SecureSharedPrefs.setContext(this)
        anon()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            when (click) {
                0 -> {
                    Snackbar.make(view!!, "Check protocols.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click++
                }
                1 -> {
                    Snackbar.make(view!!, "Deep links.", Snackbar.LENGTH_LONG)
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

        val intentToUri = intent
        val data = intentToUri.data
        val validScheme = "http" == data?.scheme || "https" == data?.scheme

        if (validScheme) {

            if (data?.scheme == "http") {
                httpToHttps()
            }
            if (data?.scheme == "https") {
                goToUrl(intent.data?.toString())
            }
        }
    }

    fun submitFlag(view: View?) {
        val editText = findViewById<EditText>(R.id.editText10)
        val post = editText.text.toString()

        childRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                if (post == value) {
                    FlagsOverview.flagSixteenButtonColor = true
                    val secure = SecureSharedPrefs()
                    secure.editBoolean(applicationContext, "flagSixteenButtonColor", true)
                    correctFlag()
                } else {
                    Toast.makeText(this@CSPBypassActivity, "Try again! :D",
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

    private fun httpToHttps() {

        val convertToHTTPS = "https://"
        val newSuperSecureURL = convertToHTTPS + intent.data?.host + intent.data?.path
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(newSuperSecureURL)
        sendRequest()
        startActivity(intent)
    }

    private fun goToUrl(secure: String?) {
        val intentSource = referrer.toString()

        if (intentSource == "android-app://com.android.chrome") {
            Log.e("BAD ORIGIN!", intentSource)
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(secure)
            startActivity(intent)
        }
    }

    private fun sendRequest() {
        val editText = findViewById<EditText>(R.id.editText10)
        val button = findViewById<Button>(R.id.button42)
        editText.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)

        val url = VGV4dEVuY3J5cHRpb25Ud28.decryptAnotherKey("kOC6ZrdMXEnfIKWihcBNLTWIhDiINUfSQyYrFsTpEBGZy1KmfPMTwtba8CXa/WVAVoJ1ACvJMd8f/MF97/7UaeNCQvC9OD4lZ/vQN6LmpBU=")
        val urlTwo = VGV4dEVuY3J5cHRpb25Ud28.decrypt(url)

        val stringRequest = StringRequest(Request.Method.GET, urlTwo,
                { response ->
                    // Display the first 500 characters of the response string.
                    textView7.text = "Response is: ${response.substring(0, 500)}"
                },
                { textView7.text = "Try another url! :D" })

        queue.add(stringRequest)
    }

    private fun anon() {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        mAuth.signInAnonymously()
    }
}