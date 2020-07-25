package b3nac.injuredandroid

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureSharedPrefs : AppCompatActivity() {

    private val preferencesName = "b3nac.injuredandroid.encrypted"

    private val spec = KeyGenParameterSpec.Builder(
            "super_secret_security_key",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()

    private val masterKey: MasterKey = MasterKey.Builder(this)
            .setKeyGenParameterSpec(spec)
            .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
            this,
            preferencesName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun editBoolean(context: Context, string: String, boolean: Boolean) {

        val editor = sharedPreferences.edit()
        editor.putBoolean(string, boolean).apply()
        editor.clear()

    }

    fun getBooleanValue(context: Context, string: String, boolean: Boolean): Boolean{

        sharedPreferences.getBoolean(string, boolean)
        return boolean
    }
}