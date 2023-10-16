package com.github.gunin_igor75.crypto_app.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CoinInfoDbModel(

    @PrimaryKey
    val fromSymbol: String,

    val toSymbol: String,

    val price: Double,

    val lastUpdate: Int,

    val highDay: Double,

    val lowDay: Double,

    val lastMarket: String,

    val imageUrl: String

)