package com.pixelbait.app.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pixelbait.app.core.notifications.NotificationHelper
import com.pixelbait.app.data.local.HistoryDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

/**
 * Tarea periódica (Requerimiento 3.4): elimina registros de más de 7 días
 * y notifica previamente al usuario cuando hay registros por vencer.
 */
@HiltWorker
class HistoryCleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val historyDao: HistoryDao,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val now = System.currentTimeMillis()
        val retentionMillis = TimeUnit.DAYS.toMillis(RETENTION_DAYS)
        val warningWindowMillis = TimeUnit.HOURS.toMillis(WARNING_HOURS_BEFORE)

        // Aviso previo: registros que vencerán dentro de las próximas WARNING_HOURS_BEFORE horas.
        val soonCutoff = now - (retentionMillis - warningWindowMillis)
        val expiringSoon = historyDao.countExpiringSoon(soonCutoff)
        if (expiringSoon > 0) {
            notificationHelper.notifyHistoryExpiringSoon()
        }

        // Borrado real de registros que ya superaron los 7 días.
        val cutoff = now - retentionMillis
        historyDao.deleteOlderThan(cutoff)

        return Result.success()
    }

    companion object {
        const val RETENTION_DAYS = 7L
        const val WARNING_HOURS_BEFORE = 24L
        const val WORK_NAME = "history_cleanup_work"
    }
}
