package pl.pw.ekartapacjenta.logic

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptedSharedPref(context: Context) {
    private var masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveJWToken(token: String?) = with(sharedPreferences.edit()) {
        putString("token", token)
        apply()
    }

    fun getJWToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun saveUserId(id: String?) = with(sharedPreferences.edit()) {
        putString("id", id)
        apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("id", null)
    }
}