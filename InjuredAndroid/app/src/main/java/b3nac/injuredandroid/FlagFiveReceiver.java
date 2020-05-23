package b3nac.injuredandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class FlagFiveReceiver extends BroadcastReceiver {
    static int wtf = 0;
    static final String ACTION_CUSTOM_BROADCAST = "com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT";


    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences settings = context.getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);

        if (wtf == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + intent.getAction() + "\n");
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n");
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

            String win = "You are a winner " + VGV4dEVuY3J5cHRpb25Ud28.decrypt("Zkdlt0WwtLQ=");
            FlagsOverview.flagFiveButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagFiveButtonColor", true).commit();
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
            
        }else{
            String win = "Keep trying!";
            Toast.makeText(context, win, Toast.LENGTH_LONG).show();
        }
    }
}