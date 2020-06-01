package b3nac.injuredandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    int click = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToXSSText(View view) {
        startActivity(new Intent(this, XSSTextActivity.class));
    }

    public void goToFlagOneLogin(View view) {
        startActivity(new Intent(this, FlagOneLoginActivity.class));
    }

    public void goToFlagTwoBypass(View view) {
        startActivity(new Intent(this, FlagTwoActivity.class));
    }

    public void goToFlagThreeResources(View view) {
        startActivity(new Intent(this, FlagThreeActivity.class));
    }

    public void goToFlagFourLogin(View view) {
        startActivity(new Intent(this, FlagFourActivity.class));
    }

    public void goToFlagFiveReceiver(View view) {
        startActivity(new Intent(this, TestBroadcastReceiver.class));
    }

    public void goToFlagSixLoginActivity(View view) {
        startActivity(new Intent(this, FlagSixLoginActivity.class));
    }

    public void goToFlagSevenSqliteActivity(View view) {
        //if (FlagsOverview.flagOneButtonColor && FlagsOverview.flagTwoButtonColor && FlagsOverview.flagThreeButtonColor && FlagsOverview.flagFourButtonColor && FlagsOverview.flagFiveButtonColor && FlagsOverview.flagSixButtonColor) {
            startActivity(new Intent(this, FlagSevenSqliteActivity.class));
        //}
    }

    public void goToFlagEightLoginActivity(View view) {
        startActivity(new Intent(this, FlagEightLoginActivity.class));
    }

    public void goToFlagNineFirebaseActivity(View view) {
        startActivity(new Intent(this, FlagNineFirebaseActivity.class));
    }

    public void goToFlagTenUnicodeActivity(View view) {
        startActivity(new Intent(this, FlagTenUnicodeActivity.class));
    }

    public void goToFlagElevenDeeplinkActivity(View view) {

        if (click == 0) {
            Toast.makeText(getApplicationContext(), "Exploit the schema!", Toast.LENGTH_LONG).show();
            click = click + 1;
        } else if (click == 1) {
            Toast.makeText(getApplicationContext(), "Check the Manifest!", Toast.LENGTH_LONG).show();
            click = 0;
        }
    }

    public void goToFlagTwelveProtectedActivity(View view) {

        if (click == 0) {
            Toast.makeText(getApplicationContext(), "Use an exported activity!", Toast.LENGTH_LONG).show();
            click = click + 1;
        } else if (click == 1) {
            Toast.makeText(getApplicationContext(), "A PoC app is needed!", Toast.LENGTH_LONG).show();
            click = click + 1;
        } else if (click == 2) {
            Toast.makeText(getApplicationContext(), "Check my Youtube :)", Toast.LENGTH_LONG).show();
            click = 0;
        }

    }

    public void goToFlagThirteenActivity(View view) {
        startActivity(new Intent(this, RCEActivity.class));
    }

    public void goToFlagFourteenActivity(View view) {
        startActivity(
                FlutterActivity
                        .withNewEngine()
                        .initialRoute("splashRoute")
                        .build(this)
        );
    }

    public void goToFlagFlagsOverview(View view) {
        startActivity(new Intent(this, FlagsOverview.class));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_contact) {
            startActivity(new Intent(this, ContactActivity.class));
        }
        if (itemId == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return true;
    }
}
