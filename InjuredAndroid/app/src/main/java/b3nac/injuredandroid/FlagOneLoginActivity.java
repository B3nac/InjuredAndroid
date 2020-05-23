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

public class FlagOneLoginActivity extends AppCompatActivity {
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_one_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0) {
                    Snackbar.make(view, "The flag is right under your nose.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = click + 1;
                } else if (click == 1) {
                    Snackbar.make(view, "The flag is also under the GUI.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = 0;
                }
            }
        });
    }

    public void submitFlag(View view) {

        SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);
        EditText editText2 = findViewById(R.id.editText2);
        String post = editText2.getText().toString();

        if (post.equals("F1ag_0n3")) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagOneButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagOneButtonColor", true).commit();
            startActivity(intent);
        }
    }
}
