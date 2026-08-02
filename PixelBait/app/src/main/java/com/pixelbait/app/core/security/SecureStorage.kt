package com.pixelbait.app.core.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Almacena la API Key de VirusTotal cifrada localmente usando Android Keystore.
 * Requerimiento 3.6: "Nunca se transmite ni almacena en servidores propios de la empresa".
 */
@Singleton
class SecureStorage @Inject constructor(
    @ApplicationContext context: Context
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "pixelbait_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveApiKey(apiKey: String) {
        prefs.edit().putString(KEY_VT_API_KEY, apiKey).apply()
    }

    fun getApiKey(): String? = prefs.getString(KEY_VT_API_KEY, null)

    fun hasApiKey(): Boolean = !getApiKey().isNullOrBlank()

    fun clearApiKey() {
        prefs.edit().remove(KEY_VT_API_KEY).apply()
    }

    companion object {
        private const val KEY_VT_API_KEY = "vt_api_key"
    }
}
