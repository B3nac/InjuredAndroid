package b3nac.injuredandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch darkModeSwitch = findViewById(R.id.switch1);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    public void clearFlags(View view) {
        SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);
        SharedPreferences flutterprefs = getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences secureprefs = getSharedPreferences("b3nac.injuredandroid.encrypted", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        SharedPreferences.Editor fluttereditor = flutterprefs.edit();
        SharedPreferences.Editor secureprefseditor = secureprefs.edit();
        editor.clear();
        editor.apply();
        fluttereditor.clear();
        fluttereditor.apply();
        secureprefseditor.clear();
        secureprefseditor.apply();
    }

}
