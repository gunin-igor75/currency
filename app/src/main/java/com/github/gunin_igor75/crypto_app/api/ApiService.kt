package com.github.gunin_igor75.crypto_app.api

import com.github.gunin_igor75.crypto_app.pojo.ContainerCurrency
import com.github.gunin_igor75.crypto_app.pojo.DataContainer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val TOKEN = "a01bed301a667416661f16738c14d4fedf448b9fa17f8e338e1f91191135e6f1"
private const val LIMIT = "limit"
private const val TSYM = "tsym"
private const val FSYMS = "fsyms"
private const val TSYMS = "tsyms"
private const val CURRENCY = "USD"

interface ApiService {

    @Headers(
        "Accept: application/json",
        "Apikey: $TOKEN"
    )
    @GET("top/totalvolfull")
    fun getDataContainer(
        @Query(LIMIT) limit: Int = 10,
        @Query(TSYM) tSym: String = CURRENCY
    ): Single<DataContainer>

    @Headers(
        "Accept: application/json",
        "Apikey: $TOKEN"
    )
    @GET("pricemultifull")
    fun getInfoCurrency(
        @Query(FSYMS) fSymS: String,
        @Query(TSYMS) symS: String = CURRENCY
    ): Single<ContainerCurrency>
}