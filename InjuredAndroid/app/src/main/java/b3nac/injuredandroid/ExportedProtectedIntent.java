package b3nac.injuredandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ExportedProtectedIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exported_protected_intent);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }

    @Override
    protected void onResume() {
        super.onResume();

        handleIntentExtras(getIntent()); // anything can be passed to getIntent() here
    }

    private void handleIntentExtras(Intent intent) {

        Intent unprotectedIntent = intent.getParcelableExtra("access_protected_component");

        assert unprotectedIntent != null;

        ComponentName name = unprotectedIntent.resolveActivity(getPackageManager());

        if (name.getPackageName().equals("b3nac.injuredandroid")) {
            startActivity(unprotectedIntent);
        }
    }
}


