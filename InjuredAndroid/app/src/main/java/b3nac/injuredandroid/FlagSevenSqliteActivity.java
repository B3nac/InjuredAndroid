package b3nac.injuredandroid;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Base64;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import b3nac.injuredandroid.DatabaseSchema.Add;
import b3nac.injuredandroid.DatabaseSchema.DataBaseHelper;

public class FlagSevenSqliteActivity extends AppCompatActivity {
    int click = 0;
    boolean correctPassword;
    DataBaseHelper dbHelper = new DataBaseHelper(this);

    private static final String TAG = "SqliteActivity";
    final String directory = "c3FsaXRl";
    final String directoryTwo = "ZjFhZy1wYTU1";
    byte[] decodedDirectoryOne = Base64.decode(directory, Base64.DEFAULT);
    byte[] decodedDirectoryTwo = Base64.decode(directoryTwo, Base64.DEFAULT);
    final String refDirectory = new String(decodedDirectoryOne, StandardCharsets.UTF_8);
    final String refDirectoryTwo = new String(decodedDirectoryTwo, StandardCharsets.UTF_8);

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef = database.child(refDirectory);
    DatabaseReference childRefTwo = database.child(refDirectoryTwo);

    private ValueEventListener mListener;
    private ValueEventListener mListenerTwo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_seven_sqlite);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0) {
                    Snackbar.make(view, "Run ADB as root.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = click + 1;
                } else if (click == 1) {
                    Snackbar.make(view, "Stay on this activity.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = 0;
                } else if (click == 2) {
                    Snackbar.make(view, "Not all encodings are the same, some need to be rotated.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                }
            }
        });

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        byte[] decode = Base64.decode("VGhlIGZsYWcgaGFzaCE=", 0);
        String str = Add.COLUMN_NAME_TITLE;
        values.put(str, decode);
        byte[] decode2 = Base64.decode("MmFiOTYzOTBjN2RiZTM0MzlkZTc0ZDBjOWIwYjE3Njc=", 0);
        String str2 = Add.COLUMN_NAME_SUBTITLE;
        values.put(str2, decode2);
        String str3 = Add.TABLE_NAME;
        db.insert(str3, null, values);
        values.put(str, Base64.decode("VGhlIGZsYWcgaXMgYWxzbyBhIHBhc3N3b3JkIQ==", 0));
        values.put(str2, Hide.getRemoteUrl());
        db.insert(str3, null, values);
    }

    public void submitFlag(View view) {

        EditText editText8 = findViewById(R.id.editText8);
        final String post = editText8.getText().toString();
        EditText editText7 = findViewById(R.id.editText7);
        final String postTwo = editText7.getText().toString();

         mListener = childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = (String) dataSnapshot.getValue();
                if (post.equals(value) && correctPassword) {
                    FlagsOverview.flagSevenButtonColor = true;
                    SecureSharedPrefs secure = new SecureSharedPrefs();
                    secure.editBoolean(getApplicationContext(), "flagSevenButtonColor", true);
                    correctFlag();
                } else {
                    Toast.makeText(FlagSevenSqliteActivity.this, "Try again! :D",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });

        mListenerTwo = childRefTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshotTwo) {
                String value = (String) dataSnapshotTwo.getValue();
                if (postTwo.equals(value)) {
                    correctPassword = true;
                } else {
                    Toast.makeText(FlagSevenSqliteActivity.this, "Try again! :D",
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

    public void onDestroy() {
        this.dbHelper.close();
        deleteDatabase(DataBaseHelper.DATABASE_NAME);
        if (mListener != null) {
            childRef.removeEventListener(mListener);
        }
        if (mListenerTwo != null) {
            childRefTwo.removeEventListener(mListenerTwo);
        }
        super.onDestroy();
    }
}
