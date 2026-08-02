package com.pixelbait.app.core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pixelbait.app.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Notificaciones locales (Requerimiento 3.5):
 * - Reinicio de cuota diaria de VirusTotal.
 * - Aviso previo a la autoeliminación del historial (7 días).
 */
@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Pixel Bait",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    fun notifyQuotaReset() {
        notify(
            id = ID_QUOTA_RESET,
            title = context.getString(R.string.app_name),
            body = "Tu cuota diaria de VirusTotal se ha reiniciado."
        )
    }

    fun notifyHistoryExpiringSoon() {
        notify(
            id = ID_HISTORY_EXPIRY,
            title = context.getString(R.string.history_expiry_notice_title),
            body = context.getString(R.string.history_expiry_notice_body)
        )
    }

    private fun notify(id: Int, title: String, body: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .build()
        NotificationManagerCompat.from(context).notify(id, notification)
    }

    companion object {
        private const val CHANNEL_ID = "pixelbait_channel"
        private const val ID_QUOTA_RESET = 1001
        private const val ID_HISTORY_EXPIRY = 1002
    }
}
