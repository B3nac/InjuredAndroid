package b3nac.injuredandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class RCEActivity extends AppCompatActivity {

    private static final String TAG = "RCEActivity";
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef = database.child("/rce");

    private FirebaseAuth mAuth;

    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rce);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        anon();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (click == 0) {
                Snackbar.make(view, "Find the binary!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Figure out how to login anonymously on click
                click = click + 1;
            } else if (click == 1) {
                Snackbar.make(view, "Permissions matter.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = click + 1;
            } else if (click == 2) {
                Snackbar.make(view, "Combine output.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = 0;
            }
        });

        if (getIntent() != null && getIntent().getData() != null) {
            copyAssets();
            Uri data = getIntent().getData();

            try {

                String intentParam = data.getQueryParameter("binary");
                String binaryParam = data.getQueryParameter("param");
                final String combinedParam = data.getQueryParameter("combined");

                childRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        String value = (String) dataSnapshot.getValue();

                        if (combinedParam != null && combinedParam.equals(value)) {
                            FlagsOverview.flagThirteenButtonColor = true;
                            SecureSharedPrefs secure = new SecureSharedPrefs();
                            secure.editBoolean(getApplicationContext(), "flagThirteenButtonColor", true);
                            correctFlag();
                        } else {
                            Toast.makeText(RCEActivity.this, "Try again! :D",
                                    Toast.LENGTH_SHORT).show();
                            }
                        }

                    @Override
                    public void onCancelled(@NotNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });

                Process process = Runtime.getRuntime().exec(getFilesDir().getParent() + "/files/"  + intentParam + " " + binaryParam);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                StringBuilder log = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    log.append(line + "\n");
                }

                TextView tv = findViewById(R.id.RCEView);
                tv.setText(log.toString());
            } catch (IOException e) {
                Log.e(TAG, "OH NO AN ERROR OCCURED!!!:" + e.getMessage());
            }
        }
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();

        String[] files = null;
        try {
            files = assetManager.list("");

        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            if (!filename.equals("webkit") && !filename.equals("images")) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(filename);
                    File outFile = new File(getFilesDir().getParent() + "/files/", filename);
                    out = new FileOutputStream(outFile);
                    copyBinary(in, out);
                } catch (IOException e) {
                    Log.e("TAG", "Failed to copy asset file: " + filename, e);
                }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        Log.e("TAG", "Failed to close file successfully: " + filename, e);
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        Log.e("TAG", "Failed to close file successfully: " + filename, e);
                    }
                }
                }
            }
        }
    }

    private void copyBinary(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    private void correctFlag() {
        Intent intent = new Intent(this, FlagOneSuccess.class);
        startActivity(intent);
    }

    private void anon() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        Toast.makeText(RCEActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

