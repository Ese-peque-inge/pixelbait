package com.pixelbait.app.core.network

import com.google.gson.annotations.SerializedName

// --- Paso 1: enviar URL a análisis (POST /urls) ---
data class SubmitUrlResponse(
    @SerializedName("data") val data: SubmitUrlData
)

data class SubmitUrlData(
    @SerializedName("id") val id: String
)

// --- Paso 2: obtener el reporte de análisis (GET /analyses/{id}) ---
data class AnalysisResponse(
    @SerializedName("data") val data: AnalysisData
)

data class AnalysisData(
    @SerializedName("id") val id: String,
    @SerializedName("attributes") val attributes: AnalysisAttributes
)

data class AnalysisAttributes(
    @SerializedName("status") val status: String, // "queued" | "completed"
    @SerializedName("stats") val stats: AnalysisStats,
    @SerializedName("results") val results: Map<String, EngineResult>?
)

data class AnalysisStats(
    @SerializedName("malicious") val malicious: Int,
    @SerializedName("suspicious") val suspicious: Int,
    @SerializedName("harmless") val harmless: Int,
    @SerializedName("undetected") val undetected: Int,
    @SerializedName("timeout") val timeout: Int = 0
)

data class EngineResult(
    @SerializedName("engine_name") val engineName: String,
    @SerializedName("category") val category: String, // "malicious" | "suspicious" | "harmless" | "undetected"
    @SerializedName("result") val result: String?
)
