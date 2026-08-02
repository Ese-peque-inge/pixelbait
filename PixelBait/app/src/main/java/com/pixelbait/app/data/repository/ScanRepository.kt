package com.pixelbait.app.data.repository

import com.pixelbait.app.core.network.VirusTotalApi
import com.pixelbait.app.core.security.SecureStorage
import com.pixelbait.app.data.local.HistoryDao
import com.pixelbait.app.data.local.HistoryEntity
import com.pixelbait.app.domain.model.EngineVerdict
import com.pixelbait.app.domain.model.RiskLevel
import com.pixelbait.app.domain.model.ScanResult
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import java.util.Base64
import javax.inject.Inject
import javax.inject.Singleton

sealed class ScanOutcome {
    data class Success(val result: ScanResult) : ScanOutcome()
    object Unverified : ScanOutcome() // sin conexión / timeout / sin respuesta
    object QuotaExceeded : ScanOutcome() // límite diario de VirusTotal alcanzado
}

/**
 * Orquesta el análisis de un enlace: envío + polling a VirusTotal con los
 * timeouts definidos en el Requerimiento 3.3 (aviso a los 5s, cancelación a los 15s).
 */
@Singleton
class ScanRepository @Inject constructor(
    private val api: VirusTotalApi,
    private val secureStorage: SecureStorage,
    private val historyDao: HistoryDao
) {

    val history: Flow<List<HistoryEntity>> = historyDao.observeAll()

    suspend fun analyzeUrl(url: String): ScanOutcome {
        val apiKey = secureStorage.getApiKey() ?: return ScanOutcome.Unverified

        return try {
            withTimeout(TIMEOUT_MILLIS) {
                val submit = api.submitUrl(apiKey, url)

                if (submit.code() == 429) return@withTimeout ScanOutcome.QuotaExceeded
                if (!submit.isSuccessful) return@withTimeout ScanOutcome.Unverified

                val analysisId = submit.body()?.data?.id ?: return@withTimeout ScanOutcome.Unverified

                var attributes = pollAnalysis(apiKey, analysisId)
                    ?: return@withTimeout ScanOutcome.Unverified

                // Poll simple mientras el análisis siga en cola.
                var attempts = 0
                while (attributes.status != "completed" && attempts < 5) {
                    delay(1500)
                    attributes = pollAnalysis(apiKey, analysisId) ?: break
                    attempts++
                }

                val stats = attributes.stats
                val engines = attributes.results?.values?.map {
                    EngineVerdict(
                        engineName = it.engineName,
                        verdict = it.result ?: it.category,
                        isThreat = it.category == "malicious" || it.category == "suspicious"
                    )
                }?.take(6) ?: emptyList()

                val total = (stats.malicious + stats.suspicious + stats.harmless + stats.undetected)
                    .coerceAtLeast(1)
                val score = ((stats.malicious * 2 + stats.suspicious) * 100 / (total * 2)).coerceIn(0, 100)

                val risk = when {
                    stats.malicious > 0 -> RiskLevel.DANGEROUS
                    stats.suspicious > 0 -> RiskLevel.SUSPICIOUS
                    else -> RiskLevel.SAFE
                }

                val result = ScanResult(
                    url = url,
                    riskLevel = risk,
                    criticalityScore = score,
                    maliciousCount = stats.malicious,
                    suspiciousCount = stats.suspicious,
                    cleanCount = stats.harmless + stats.undetected,
                    engines = engines,
                    analyzedAtMillis = System.currentTimeMillis(),
                    reportUrl = "https://www.virustotal.com/gui/url/${urlToVtId(url)}"
                )

                saveToHistory(result)
                ScanOutcome.Success(result)
            }
        } catch (e: TimeoutCancellationException) {
            ScanOutcome.Unverified
        } catch (e: Exception) {
            ScanOutcome.Unverified
        }
    }

    private suspend fun pollAnalysis(apiKey: String, analysisId: String) =
        api.getAnalysis(apiKey, analysisId).takeIf { it.isSuccessful }?.body()?.data?.attributes

    private fun urlToVtId(url: String): String =
        Base64.getUrlEncoder().withoutPadding().encodeToString(url.toByteArray()).trimEnd('=')

    private suspend fun saveToHistory(result: ScanResult) {
        historyDao.insert(
            HistoryEntity(
                url = result.url,
                riskLevel = result.riskLevel.name,
                criticalityScore = result.criticalityScore,
                maliciousCount = result.maliciousCount,
                suspiciousCount = result.suspiciousCount,
                cleanCount = result.cleanCount,
                analyzedAtMillis = result.analyzedAtMillis
            )
        )
    }

    suspend fun clearHistory() = historyDao.clearAll()

    companion object {
        // Requerimiento 3.3 / 3.8: timeout de cancelación de 15 segundos.
        const val TIMEOUT_MILLIS = 15_000L
        // Requerimiento 3.3 / 3.8: aviso de demora a los 5 segundos (usado en la UI).
        const val SLOW_WARNING_MILLIS = 5_000L
    }
}
