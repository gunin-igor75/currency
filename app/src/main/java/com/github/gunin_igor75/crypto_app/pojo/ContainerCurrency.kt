package com.github.gunin_igor75.crypto_app.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContainerCurrency(

    @SerializedName("RAW")
    @Expose
    val containerCurrencyJson: JsonObject?
)
