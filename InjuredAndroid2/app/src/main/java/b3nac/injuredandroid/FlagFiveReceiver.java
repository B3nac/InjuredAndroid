package b3nac.injuredandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class FlagFiveReceiver extends BroadcastReceiver {
    static int wtf = 0;
    static final String ACTION_CUSTOM_BROADCAST = "com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (wtf == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + intent.getAction() + "\n");
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
            String log = sb.toString();
            Log.d("DUDE!:", log);
            Toast.makeText(context, log, Toast.LENGTH_LONG).show();
            wtf = wtf + 1;
        }
        else if (wtf == 1) {
            String win = "Keep trying!";
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
            wtf = wtf + 1;
        }
        else if (wtf == 2) {
                String win = "You are a winner";
                Toast.makeText(context, win, Toast.LENGTH_LONG).show();
        }else{
            String win = "Keep trying!";
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
        }
    }
}