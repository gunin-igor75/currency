package com.github.gunin_igor75.crypto_app.data.network.retro

import com.github.gunin_igor75.crypto_app.data.network.dto.CoinInfoJsonContainerDto
import com.github.gunin_igor75.crypto_app.data.network.dto.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("top/totalvolfull")
    suspend fun getCoinNameList(
        @Query(LIMIT) limit: Int = 10,
        @Query(TSYM) tSym: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getInfoCurrency(
        @Query(FSYMS) fSymS: String,
        @Query(TSYMS) symS: String = CURRENCY
    ): CoinInfoJsonContainerDto

    companion object{
        private const val LIMIT = "limit"
        private const val TSYM = "tsym"
        private const val FSYMS = "fsyms"
        private const val TSYMS = "tsyms"
        private const val CURRENCY = "USD"
    }
}