package b3nac.injuredandroid

import android.app.Application
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureSharedPrefs : Application() {

    companion object {

        private lateinit var context: Context

        fun setContext(con: Context) {
            context = con
        }
    }

    private val preferencesName = "b3nac.injuredandroid.encrypted"

    var masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            preferencesName,
            masterKey, // masterKey created above
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    fun editBoolean(context: Context, string: String, boolean: Boolean) {

        val editor = sharedPreferences.edit()
        editor.putBoolean(string, boolean).apply()
        editor.clear()

    }

    fun getBooleanValue(context: Context, string: String, boolean: Boolean): Boolean{

        sharedPreferences.getBoolean(string, boolean)
        return boolean
    }

    fun putString(context: Context, string: String, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(string, value).apply()
        editor.clear()
    }

    fun getString(s: String, s1: String): String? {

        return sharedPreferences.getString(s, s1)

    }
}