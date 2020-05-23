package b3nac.injuredandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.widget.EditText;

public class FlagSixLoginActivity extends AppCompatActivity {
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_six_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0) {
                    Snackbar.make(view, "Keys.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = click + 1;
                } else if (click == 1) {
                    Snackbar.make(view, "Classes.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = 0;
                }
            }
        });
    }

    public void submitFlag(View view) {

        SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);
        EditText editText3 = findViewById(R.id.editText3);
        String post = editText3.getText().toString();

        if (post.equals(VGV4dEVuY3J5cHRpb25Ud28.decrypt("k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ=="))) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagSixButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagSixButtonColor", true).commit();
            startActivity(intent);
        }
    }

}
