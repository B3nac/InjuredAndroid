package b3nac.injuredandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FlagFiveActivity extends AppCompatActivity {

    private FlagFiveReceiver fReceiver = new FlagFiveReceiver();
    private ComponentName mReceiverComponentName;
    private PackageManager mPackageManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_five);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button broadcastButton = (Button) findViewById(R.id.button9);

        //Get the PackageManager and ComponentName so you can toggle to broadcast receiver.
        mReceiverComponentName = new ComponentName(this, FlagFiveReceiver.class);
        mPackageManager = getPackageManager();

        //Use LocalBroadcastManager so that the broadcast is not received by other applications.
        LocalBroadcastManager.getInstance(this).registerReceiver
                (fReceiver, new IntentFilter(FlagFiveReceiver.ACTION_CUSTOM_BROADCAST));

        //onClick method for the button
        broadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                broadcastIntent();
            }
        });
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(fReceiver);
        super.onDestroy();
    }
    public void broadcastIntent() {
        Intent intent = new Intent(FlagFiveReceiver.ACTION_CUSTOM_BROADCAST);
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        sendBroadcast(intent);
    }

}
