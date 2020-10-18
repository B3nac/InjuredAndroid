package b3nac.injuredandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class FlagFiveReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        SecureSharedPrefs.setContext(context)

        if (click == 0) {
            val sb = StringBuilder()
            sb.append("""
    Action: ${intent.action}

    """.trimIndent())
            sb.append("""
    URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}

    """.trimIndent())
            val log = sb.toString()
            Log.d("DUDE!:", log)
            Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            click++
        } else if (click == 1) {
            val win = "Keep trying!"
            Toast.makeText(context, win, Toast.LENGTH_LONG).show()
            click++
        } else if (click == 2) {
            val win = "You are a winner " + VGV4dEVuY3J5cHRpb25Ud28.decrypt("Zkdlt0WwtLQ=")
            FlagsOverview().flagFiveButtonColor = true
            SecureSharedPrefs().editBoolean(context, "flagFiveButtonColor", true)
            Toast.makeText(context, win, Toast.LENGTH_LONG).show()
            click = 0
        } else {
            val win = "Keep trying!"
            Toast.makeText(context, win, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        var click = 0
        const val ACTION_CUSTOM_BROADCAST = "com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT"
    }
}