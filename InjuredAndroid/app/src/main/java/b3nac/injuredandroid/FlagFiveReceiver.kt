package b3nac.injuredandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class FlagFiveReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val settings = context.getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE)
        if (wtf == 0) {
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
            wtf = wtf + 1
        } else if (wtf == 1) {
            val win = "Keep trying!"
            Toast.makeText(context, win, Toast.LENGTH_LONG).show()
            wtf = wtf + 1
        } else if (wtf == 2) {
            val win = "You are a winner " + VGV4dEVuY3J5cHRpb25Ud28.decrypt("Zkdlt0WwtLQ=")
            FlagsOverview().flagFiveButtonColor = true
            SecureSharedPrefs().editBoolean(context, "flagFiveButtonColor", true)
            Toast.makeText(context, win, Toast.LENGTH_LONG).show()
        } else {
            val win = "Keep trying!"
            Toast.makeText(context, win, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        var wtf = 0
        const val ACTION_CUSTOM_BROADCAST = "com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT"
    }
}