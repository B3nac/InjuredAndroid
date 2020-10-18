package b3nac.injuredandroid

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import b3nac.injuredandroid.DatabaseSchema.Add
import b3nac.injuredandroid.DatabaseSchema.DataBaseHelper
import b3nac.injuredandroid.FlagSevenSqliteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.nio.charset.StandardCharsets

class FlagSevenSqliteActivity : AppCompatActivity() {
    var click = 0
    var dbHelper = DataBaseHelper(this)
    val directory = "c3FsaXRl"
    val directoryTwo = "ZjFhZy1wYTU1"
    var decodedDirectoryOne = Base64.decode(directory, Base64.DEFAULT)
    var decodedDirectoryTwo = Base64.decode(directoryTwo, Base64.DEFAULT)
    val refDirectory = String(decodedDirectoryOne, StandardCharsets.UTF_8)
    val refDirectoryTwo = String(decodedDirectoryTwo, StandardCharsets.UTF_8)
    var database = FirebaseDatabase.getInstance().reference
    var childRef = database.child(refDirectory)
    var childRefTwo = database.child(refDirectoryTwo)
    private var mListener: ValueEventListener? = null
    private var mListenerTwo: ValueEventListener? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_seven_sqlite)
        setSupportActionBar(findViewById(R.id.toolbar))
        SecureSharedPrefs.setContext(this)
        preloadValues()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            when (click) {
                0 -> {
                    Snackbar.make(view!!, "Run ADB as root.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click++
                }
                1 -> {
                    Snackbar.make(view!!, "Stay on this activity.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    click = 0
                }
                2 -> {
                    Snackbar.make(view!!, "Not all encodings are the same, some need to be rotated.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
            }
        }

        val db = dbHelper.writableDatabase
        val values = ContentValues()
        val decode = Base64.decode("VGhlIGZsYWcgaGFzaCE=", 0)
        val str = Add.COLUMN_NAME_TITLE
        values.put(str, decode)
        val decode2 = Base64.decode("MmFiOTYzOTBjN2RiZTM0MzlkZTc0ZDBjOWIwYjE3Njc=", 0)
        val str2 = Add.COLUMN_NAME_SUBTITLE
        values.put(str2, decode2)
        val str3 = Add.TABLE_NAME
        db.insert(str3, null, values)
        values.put(str, Base64.decode("VGhlIGZsYWcgaXMgYWxzbyBhIHBhc3N3b3JkIQ==", 0))
        values.put(str2, Hide.getRemoteUrl())
        db.insert(str3, null, values)
    }

    fun submitFlag(view: View?) {
        val editText8 = findViewById<EditText>(R.id.editText8)
        val post = editText8.text.toString()
        val editText7 = findViewById<EditText>(R.id.editText7)
        val postTwo = editText7.text.toString()
        val secure = SecureSharedPrefs()
        val flagSevenValue = secure.getString("flagSevenEncrypted", "")
        val flagSevenPassword = secure.getString("flagSevenPasswordEncrypted", "")

        if (post == flagSevenValue && postTwo == flagSevenPassword) {
            FlagsOverview.flagSevenButtonColor = true
            val secure = SecureSharedPrefs()
            secure.editBoolean(applicationContext, "flagSevenButtonColor", true)
            correctFlag()
        } else {
            Toast.makeText(this@FlagSevenSqliteActivity, "Try again! :D",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun preloadValues() {
        mListener = childRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String?
                val secure = SecureSharedPrefs()
                secure.putString(applicationContext, "flagSevenEncrypted", value)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException())
            }
        })
        mListenerTwo = childRefTwo.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshotTwo: DataSnapshot) {
                val value = dataSnapshotTwo.value as String?
                val secure = SecureSharedPrefs()
                secure.putString(applicationContext, "flagSevenPasswordEncrypted", value)
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

    public override fun onDestroy() {
        dbHelper.close()
        deleteDatabase(DataBaseHelper.DATABASE_NAME)
        if (mListener != null) {
            childRef.removeEventListener(mListener!!)
        }
        if (mListenerTwo != null) {
            childRefTwo.removeEventListener(mListenerTwo!!)
        }
        super.onDestroy()
    }

    companion object {
        private const val TAG = "SqliteActivity"
    }
}