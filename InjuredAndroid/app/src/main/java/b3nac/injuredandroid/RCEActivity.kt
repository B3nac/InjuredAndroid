package b3nac.injuredandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import java.io.*

class RCEActivity : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child("/rce")

    var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rce)
        SecureSharedPrefs.setContext(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        anon()
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            if (click == 0) {
                Snackbar.make(view!!, "Find the binary!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                //Figure out how to login anonymously on click
                click++
            } else if (click == 1) {
                Snackbar.make(view!!, "Permissions matter.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click++
            } else if (click == 2) {
                Snackbar.make(view!!, "Combine output.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                click = 0
            }
        }
        if (intent != null && intent.data != null) {
            copyAssets()
            val data = intent.data
            try {
                val intentParam = data!!.getQueryParameter("binary")
                val binaryParam = data.getQueryParameter("param")
                val combinedParam = data.getQueryParameter("combined")
                if (combinedParam != null) {
                    childRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val value = dataSnapshot.value as String?
                            if (combinedParam == value) {
                                FlagsOverview.flagThirteenButtonColor = true
                                val secure = SecureSharedPrefs()
                                secure.editBoolean(applicationContext, "flagThirteenButtonColor", true)
                                correctFlag()
                            } else {
                                Toast.makeText(this@RCEActivity, "Try again! :D",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException())
                        }
                    })
                }
                else {

                    val process = Runtime.getRuntime().exec(filesDir.parent + "/files/" + intentParam + " " + binaryParam)
                    val bufferedReader = BufferedReader(
                            InputStreamReader(process.inputStream))
                    val log = StringBuilder()
                    bufferedReader.forEachLine {
                        log.append(it)
                    }
                    process.waitFor()
                    val tv = findViewById<TextView>(R.id.RCEView)
                    tv.text = log.toString()
                }
            } catch (e: IOException) {
                Log.e(TAG, "OH NO AN ERROR OCCURED!!!:" + e.message)
            }
        }
    }

    private fun copyAssets() {
        val assetManager = assets
        var files: Array<String>? = null
        try {
            files = assetManager.list("")
        } catch (e: IOException) {
            Log.e("tag", "Failed to get asset file list.", e)
        }
        if (files != null) for (filename in files) {
            if (filename != "webkit" && filename != "images" && filename != "flutter_assets" && filename != "locales") {
                var `in`: InputStream? = null
                var out: OutputStream? = null
                try {
                    `in` = assetManager.open(filename)
                    val outFile = File(filesDir.parent + "/files/", filename)
                    out = FileOutputStream(outFile)
                    copyBinary(`in`, out)
                } catch (e: IOException) {
                    Log.e("TAG", "Failed to copy asset file: $filename", e)
                } finally {
                    if (`in` != null) {
                        try {
                            `in`.close()
                        } catch (e: IOException) {
                            Log.e("TAG", "Failed to close file successfully: $filename", e)
                        }
                    }
                    if (out != null) {
                        try {
                            out.close()
                        } catch (e: IOException) {
                            Log.e("TAG", "Failed to close file successfully: $filename", e)
                        }
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun copyBinary(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }

    private fun correctFlag() {
        val intent = Intent(this, FlagOneSuccess::class.java)
        startActivity(intent)
    }

    private fun anon() {
            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
            mAuth.signInAnonymously()
    }

    companion object {
        private const val TAG = "RCEActivity"
    }
}