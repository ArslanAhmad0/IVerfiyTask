package com.iverify.task.data.util.tokenmanager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SecureTokenManager @Inject constructor(
    @ApplicationContext context: Context
) : TokenManager {
    companion object {
        private const val PREF_FILE_NAME = "iVerify_prefs"
        private const val KEY_TOKEN = "token"
    }

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREF_FILE_NAME,
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveToken(token: String) {
        sharedPreferences.edit(true) {
            putString(KEY_TOKEN, token)
        }
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
}