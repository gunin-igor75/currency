package com.github.gunin_igor75.crypto_app.data.network

import com.github.gunin_igor75.crypto_app.data.network.dto.CoinInfoJsonContainerDto
import com.github.gunin_igor75.crypto_app.data.network.dto.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query



interface ApiService {

    @Headers(
        "Accept: application/json",
        "Apikey: $TOKEN"
    )
    @GET("top/totalvolfull")
    suspend fun getCoinNameList(
        @Query(LIMIT) limit: Int = 10,
        @Query(TSYM) tSym: String = CURRENCY
    ): CoinNamesListDto

    @Headers(
        "Accept: application/json",
        "Apikey: $TOKEN"
    )
    @GET("pricemultifull")
    suspend fun getInfoCurrency(
        @Query(FSYMS) fSymS: String,
        @Query(TSYMS) symS: String = CURRENCY
    ): CoinInfoJsonContainerDto

    companion object{
        private const val TOKEN = "a01bed301a667416661f16738c14d4fedf448b9fa17f8e338e1f91191135e6f1"
        private const val LIMIT = "limit"
        private const val TSYM = "tsym"
        private const val FSYMS = "fsyms"
        private const val TSYMS = "tsyms"
        private const val CURRENCY = "USD"
    }
}