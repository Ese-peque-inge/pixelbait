package com.pixelbait.app.domain.model

/**
 * Semáforo de riesgo de 3 niveles (Requerimiento 3.2.3).
 * Debe comunicarse siempre con color + ícono + texto (accesibilidad daltonismo).
 */
enum class RiskLevel {
    SAFE,       // Verde — Seguro
    SUSPICIOUS, // Ámbar — Sospechoso / Moderado
    DANGEROUS,  // Rojo — Peligroso / Malicioso
    UNVERIFIED  // Sin conexión / timeout / cuota agotada
}

data class EngineVerdict(
    val engineName: String,
    val verdict: String, // p.ej. "Phishing", "Malicious", "Clean"
    val isThreat: Boolean
)

data class ScanResult(
    val url: String,
    val riskLevel: RiskLevel,
    val criticalityScore: Int, // 0..100
    val maliciousCount: Int,
    val suspiciousCount: Int,
    val cleanCount: Int,
    val engines: List<EngineVerdict>,
    val analyzedAtMillis: Long,
    val reportUrl: String? = null
)
