package com.pixelbait.app.core.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Cliente Retrofit para la API pública v3 de VirusTotal.
 * La API Key se envía en el header "x-apikey" (siempre la del propio usuario,
 * nunca una clave propia de Pixel Bait — Requerimiento 3.6).
 */
interface VirusTotalApi {

    @FormUrlEncoded
    @POST("urls")
    suspend fun submitUrl(
        @Header("x-apikey") apiKey: String,
        @Field("url") url: String
    ): Response<SubmitUrlResponse>

    @GET("analyses/{id}")
    suspend fun getAnalysis(
        @Header("x-apikey") apiKey: String,
        @Path("id") analysisId: String
    ): Response<AnalysisResponse>
}
