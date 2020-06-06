package b3nac.injuredandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;

public class FlagEightLoginActivity extends AppCompatActivity {
    int click = 0;
    private static final String TAG = "FlagEightLoginActivity";

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef = database.child("/aws");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_eight_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(FlagEightLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0) {
                    Snackbar.make(view, "AWS CLI.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = click + 1;
                } else if (click == 1) {
                    Snackbar.make(view, "AWS profiles and credentials.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = 0;
                }
            }
        });
    }

    public void submitFlag(View view) {

        final SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);
        EditText editText2 = findViewById(R.id.editText9);
        final String post = editText2.getText().toString();

        childRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = (String) dataSnapshot.getValue();
                if (post.equals(value)) {
                    FlagsOverview.flagEightButtonColor = true;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("flagEightButtonColor", true).apply();
                    correctFlag();
                } else {
                    Toast.makeText(FlagEightLoginActivity.this, "Try again! :D",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private void correctFlag() {
        Intent intent = new Intent(this, FlagOneSuccess.class);
        startActivity(intent);
    }

}
