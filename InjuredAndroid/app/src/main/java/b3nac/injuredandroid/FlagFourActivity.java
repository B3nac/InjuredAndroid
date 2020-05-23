package b3nac.injuredandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.widget.EditText;

public class FlagFourActivity extends AppCompatActivity {
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_four);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click == 0) {
                    Snackbar.make(view, "Where is bob.", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    click = click + 1;
                } else if (click == 1) {
                    Snackbar.make(view, "Classes and imports.", Snackbar.LENGTH_LONG)
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
        Decoder decoder = new Decoder();

        String bob = new String(decoder.getData());

        if (post.equals(bob)) {
            Intent intent = new Intent(this, FlagOneSuccess.class);
            FlagsOverview.flagFourButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagFourButtonColor", true).commit();
            startActivity(intent);
        }
    }

}
