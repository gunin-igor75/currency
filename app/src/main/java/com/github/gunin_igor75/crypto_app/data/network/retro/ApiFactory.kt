package com.github.gunin_igor75.crypto_app.data.network.retro

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"

    private const val TOKEN = "a01bed301a667416661f16738c14d4fedf448b9fa17f8e338e1f91191135e6f1"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(createAuthorizationInterceptor())
        .addInterceptor(createLoggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    private fun createAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            newBuilder.addHeader("Authorization", TOKEN)
            return@Interceptor chain.proceed(newBuilder.build())
        }
    }
    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}