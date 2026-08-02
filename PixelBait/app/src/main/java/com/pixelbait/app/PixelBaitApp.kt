package com.pixelbait.app

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.pixelbait.app.data.worker.HistoryCleanupWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class PixelBaitApp : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        scheduleHistoryCleanup()
    }

    private fun scheduleHistoryCleanup() {
        val request = PeriodicWorkRequestBuilder<HistoryCleanupWorker>(6, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            HistoryCleanupWorker.WORK_NAME,
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }
}
