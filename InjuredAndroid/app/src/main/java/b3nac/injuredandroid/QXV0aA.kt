package b3nac.injuredandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class QXV0aA : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qxv0a)
    }

    fun signInAnonymously(view: View) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@QXV0aA, "Authentication succeeded.",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@QXV0aA, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }
                }
    }
}
