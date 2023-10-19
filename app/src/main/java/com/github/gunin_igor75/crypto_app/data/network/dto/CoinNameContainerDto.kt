package com.github.gunin_igor75.crypto_app.data.network.dto

import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(

    @SerializedName("CoinInfo")
    val coinNameDto: CoinNameDto? = null
)
