package com.pixelbait.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(entity: HistoryEntity)

    @Query("SELECT * FROM scan_history ORDER BY analyzedAtMillis DESC")
    fun observeAll(): Flow<List<HistoryEntity>>

    @Query("DELETE FROM scan_history")
    suspend fun clearAll()

    // Requerimiento 3.4: retención de 7 días.
    @Query("DELETE FROM scan_history WHERE analyzedAtMillis < :cutoffMillis")
    suspend fun deleteOlderThan(cutoffMillis: Long)

    // Usado para el aviso previo a la autoeliminación (Requerimiento 3.5).
    @Query("SELECT COUNT(*) FROM scan_history WHERE analyzedAtMillis < :soonCutoffMillis")
    suspend fun countExpiringSoon(soonCutoffMillis: Long): Int
}
