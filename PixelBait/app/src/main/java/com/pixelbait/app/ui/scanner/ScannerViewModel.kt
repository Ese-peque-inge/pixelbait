package com.pixelbait.app.ui.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelbait.app.data.repository.ScanOutcome
import com.pixelbait.app.data.repository.ScanRepository
import com.pixelbait.app.domain.model.ScanResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ScanUiState {
    object Idle : ScanUiState()
    object Detecting : ScanUiState()
    data class Verifying(val slow: Boolean) : ScanUiState()
    data class Result(val result: ScanResult) : ScanUiState()
    object Unverified : ScanUiState()
    object QuotaExceeded : ScanUiState()
}

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scanRepository: ScanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScanUiState>(ScanUiState.Idle)
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    private var lastScannedUrl: String? = null

    fun onQrDetected(url: String) {
        // Evita relanzar el análisis repetidamente mientras el QR sigue en cuadro.
        if (_uiState.value !is ScanUiState.Idle) return
        if (url == lastScannedUrl) return
        lastScannedUrl = url

        viewModelScope.launch {
            _uiState.value = ScanUiState.Verifying(slow = false)

            // Requerimiento 3.3: aviso de demora a los 5 segundos.
            val slowWarningJob = launch {
                delay(ScanRepository.SLOW_WARNING_MILLIS)
                if (_uiState.value is ScanUiState.Verifying) {
                    _uiState.value = ScanUiState.Verifying(slow = true)
                }
            }

            when (val outcome = scanRepository.analyzeUrl(url)) {
                is ScanOutcome.Success -> _uiState.value = ScanUiState.Result(outcome.result)
                ScanOutcome.Unverified -> _uiState.value = ScanUiState.Unverified
                ScanOutcome.QuotaExceeded -> _uiState.value = ScanUiState.QuotaExceeded
            }
            slowWarningJob.cancel()
        }
    }

    fun dismiss() {
        _uiState.value = ScanUiState.Idle
        lastScannedUrl = null
    }
}
