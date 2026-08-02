package com.pixelbait.app.di

import com.pixelbait.app.BuildConfig
import com.pixelbait.app.core.network.VirusTotalApi
import com.pixelbait.app.data.repository.ScanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            // Requerimiento 3.3/3.8: timeout de consulta a VirusTotal = 15 segundos.
            .connectTimeout(ScanRepository.TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(ScanRepository.TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(ScanRepository.TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.VT_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideVirusTotalApi(retrofit: Retrofit): VirusTotalApi =
        retrofit.create(VirusTotalApi::class.java)
}
