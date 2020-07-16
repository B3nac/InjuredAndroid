package b3nac.injuredandroid;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AssemblyActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tv = findViewById(R.id.textView10);
        setSupportActionBar(toolbar);

        String XORstring = stringFromJNI();
        byte[] bytes = XORstring.getBytes();

        tv.setText(Arrays.toString(bytes));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (click == 0) {
                Snackbar.make(view, "Covert the byte array to a string.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                click = click + 1;
            } else if (click == 1) {
                Snackbar.make(view, "Disassemble Shared Object file to find the XOR key.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = click + 1;
            } else if (click == 2) {
                Snackbar.make(view, "Reverse the XOR string.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = 0;
            }
        });
    }

    public native String stringFromJNI();
}
