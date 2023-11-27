package com.github.gunin_igor75.crypto_app.domain.repository

import androidx.lifecycle.LiveData
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfoByName(fSym: String): LiveData<CoinInfo>

    fun loadData()

}