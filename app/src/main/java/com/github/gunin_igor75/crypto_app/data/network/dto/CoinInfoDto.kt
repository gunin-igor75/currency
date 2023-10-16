package com.github.gunin_igor75.crypto_app.data.network.dto

import com.google.gson.annotations.SerializedName


data class CoinInfoDto(

    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String,


    @SerializedName("PRICE")
    val price: Double,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Int,


    @SerializedName("HIGHDAY")
    val highDay: Double,

    @SerializedName("LOWDAY")
    val lowDay: Double,

    @SerializedName("LASTMARKET")
    val lastMarket: String,

    @SerializedName("IMAGEURL")
    val imageUrl: String

)