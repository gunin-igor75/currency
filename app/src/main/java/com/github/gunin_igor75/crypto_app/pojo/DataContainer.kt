package com.github.gunin_igor75.crypto_app.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataContainer(

    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)
