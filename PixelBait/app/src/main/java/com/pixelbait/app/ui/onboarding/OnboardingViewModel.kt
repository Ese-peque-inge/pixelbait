package com.pixelbait.app.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelbait.app.core.security.SecureStorage
import com.pixelbait.app.data.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val _apiKeyInput = MutableStateFlow("")
    val apiKeyInput: StateFlow<String> = _apiKeyInput.asStateFlow()

    private val _showApiKeyError = MutableStateFlow(false)
    val showApiKeyError: StateFlow<Boolean> = _showApiKeyError.asStateFlow()

    fun onApiKeyChanged(value: String) {
        _apiKeyInput.value = value
        if (value.isNotBlank()) _showApiKeyError.value = false
    }

    fun acceptTerms(onDone: () -> Unit) {
        viewModelScope.launch {
            onboardingRepository.setTermsAccepted(true)
            onDone()
        }
    }

    fun activateProtection(onDone: () -> Unit) {
        val key = _apiKeyInput.value.trim()
        if (key.isBlank()) {
            _showApiKeyError.value = true
            return
        }
        secureStorage.saveApiKey(key)
        viewModelScope.launch {
            onboardingRepository.setOnboardingComplete(true)
            onDone()
        }
    }
}
