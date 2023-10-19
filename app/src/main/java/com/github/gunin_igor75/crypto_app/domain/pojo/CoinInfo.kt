package com.github.gunin_igor75.crypto_app.domain.pojo


data class CoinInfo(

    val fromSymbol: String,

    val toSymbol: String,

    val price: Double,

    val lastUpdate: String,

    val highDay: Double,

    val lowDay: Double,

    val lastMarket: String,

    val imageUrl: String

)