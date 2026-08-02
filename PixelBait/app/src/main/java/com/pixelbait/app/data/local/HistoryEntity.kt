package com.pixelbait.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Registro de historial local (Requerimiento 3.4).
 * Se elimina automáticamente a los 7 días vía HistoryCleanupWorker.
 */
@Entity(tableName = "scan_history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val url: String,
    val riskLevel: String, // nombre del enum RiskLevel
    val criticalityScore: Int,
    val maliciousCount: Int,
    val suspiciousCount: Int,
    val cleanCount: Int,
    val analyzedAtMillis: Long
)
