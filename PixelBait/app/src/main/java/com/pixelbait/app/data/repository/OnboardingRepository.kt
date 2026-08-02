package com.pixelbait.app.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "pixelbait_onboarding")

/**
 * Persiste si el usuario ya aceptó los Términos y Condiciones, para no
 * volver a mostrarlos en aperturas posteriores (Requerimiento 3.6).
 */
@Singleton
class OnboardingRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val termsAcceptedKey = booleanPreferencesKey("terms_accepted")
    private val onboardingCompleteKey = booleanPreferencesKey("onboarding_complete")

    val termsAccepted: Flow<Boolean> =
        context.dataStore.data.map { it[termsAcceptedKey] ?: false }

    val onboardingComplete: Flow<Boolean> =
        context.dataStore.data.map { it[onboardingCompleteKey] ?: false }

    suspend fun setTermsAccepted(accepted: Boolean) {
        context.dataStore.edit { it[termsAcceptedKey] = accepted }
    }

    suspend fun setOnboardingComplete(complete: Boolean) {
        context.dataStore.edit { it[onboardingCompleteKey] = complete }
    }
}
