package com.pixelbait.app.di

import android.content.Context
import androidx.room.Room
import com.pixelbait.app.data.local.AppDatabase
import com.pixelbait.app.data.local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "pixelbait.db").build()

    @Provides
    @Singleton
    fun provideHistoryDao(db: AppDatabase): HistoryDao = db.historyDao()
}
