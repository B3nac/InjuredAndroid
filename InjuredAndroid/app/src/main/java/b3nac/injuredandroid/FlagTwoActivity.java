package b3nac.injuredandroid;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FlagTwoActivity extends AppCompatActivity {
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_two);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (click == 0) {
                Snackbar.make(view, "Key words Activity and exported.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = click + 1;
            } else if (click == 1) {
                Snackbar.make(view, "Exported Activities can be accessed with adb or Drozer.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = 0;
            }
        });
    }

}
