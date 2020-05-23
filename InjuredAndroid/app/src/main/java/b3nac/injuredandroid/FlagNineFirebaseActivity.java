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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;

public class FlagNineFirebaseActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    int click = 0;
    private static final String TAG = "FirebaseActivity";
    final String directory = "ZmxhZ3Mv";
    byte[] decodedDirectory = Base64.decode(directory, Base64.DEFAULT);
    final String refDirectory = new String(decodedDirectory, StandardCharsets.UTF_8);

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef = database.child(refDirectory);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_nine_firebase);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0) {
                    Snackbar.make(view, "Use the .json trick with database url", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //Figure out how to login anonymously on click
                    click = click + 1;
                } else if (click == 1) {
                    Snackbar.make(view, "Filenames.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = click + 1;
                } else if (click == 2) {
                    Snackbar.make(view, "Encoding.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = 0;
                }
            }
        });
    }

    public void submitFlag(View view) {

        final SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);
        EditText editText2 = findViewById(R.id.editText2);
        final String post = editText2.getText().toString();
        byte[] decodedPost = Base64.decode(post, Base64.DEFAULT);
        final String decoded = new String(decodedPost, StandardCharsets.UTF_8);

        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = (String) dataSnapshot.getValue();
                if (decoded.equals(value)) {
                    FlagsOverview.flagNineButtonColor = true;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("flagNineButtonColor", true).commit();
                    logFlagFound();
                    correctFlag();
                } else {
                    Toast.makeText(FlagNineFirebaseActivity.this, "Try again! :D",
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
    private void logFlagFound() {
        //Firebase analytics
        String text = "Someone found the flag!";
        Bundle params = new Bundle();
        params.putString("full_text", text);
        mFirebaseAnalytics.logEvent("Flag_found", params);
    }
}

