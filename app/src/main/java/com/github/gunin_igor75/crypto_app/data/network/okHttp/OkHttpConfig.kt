package com.github.gunin_igor75.crypto_app.data.network.okHttp

import com.google.gson.Gson
import okhttp3.OkHttpClient

class OkHttpConfig(
    val baseUrl: String,
    val client: OkHttpClient,
    val gson: Gson
)