package b3nac.injuredandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.widget.Button;

public class FlagFiveActivity extends AppCompatActivity {

    int click = 0;
    private FlagFiveReceiver fReceiver = new FlagFiveReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_five);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (click == 0) {
                Snackbar.make(view, "Where is bob.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = click + 1;
            } else if (click == 1) {
                Snackbar.make(view, "Classes and imports.", Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                click = 0;
            }
        });

        Button broadcastButton = findViewById(R.id.button9);

        //Get the PackageManager and ComponentName so you can toggle to broadcast receiver.
        ComponentName mReceiverComponentName = new ComponentName(this, FlagFiveReceiver.class);
        PackageManager mPackageManager = getPackageManager();

        //Use LocalBroadcastManager so that the broadcast is not received by other applications.
        LocalBroadcastManager.getInstance(this).registerReceiver
                (fReceiver, new IntentFilter(FlagFiveReceiver.ACTION_CUSTOM_BROADCAST));

        //onClick method for the button
        broadcastButton.setOnClickListener(view -> broadcastIntent());
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(fReceiver);
        super.onDestroy();
    }
    public void broadcastIntent() {
        Intent intent = new Intent(FlagFiveReceiver.ACTION_CUSTOM_BROADCAST);
        sendBroadcast(intent);
    }

}
