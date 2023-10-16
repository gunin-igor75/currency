package com.github.gunin_igor75.crypto_app.data.network.dto

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerDto(

    @SerializedName("RAW")
    val json: JsonObject?
)
