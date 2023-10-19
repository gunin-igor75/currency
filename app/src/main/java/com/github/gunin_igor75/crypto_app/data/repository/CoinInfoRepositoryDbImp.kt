package com.github.gunin_igor75.crypto_app.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.github.gunin_igor75.crypto_app.data.db.AppDatabase
import com.github.gunin_igor75.crypto_app.data.mapper.CoinMapper
import com.github.gunin_igor75.crypto_app.data.network.ApiFactory.apiService
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinInfoRepositoryDbImp (application: Application) : CoinRepository {

    private val appDao = AppDatabase.getInstance(application).appDao()

    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return appDao.getAllCurrency().map { mapper.mapDbModelsToPojoList(it) }
    }

    override fun getCoinInfoByName(fSym: String): LiveData<CoinInfo> {
        return appDao.getCurrencyByName(fSym).map { mapper.mapDbModelToPojo(it) }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val coins = apiService.getCoinNameList()
                val namesString = mapper.mapCoinNameListToString(coins)
                val json = apiService.getInfoCurrency(namesString)
                val coinsInfoDto = mapper.mapJsonToList(json)
                val coinsInfo = coinsInfoDto.map { mapper.mapDtoToDbModel(it) }
                appDao.saveCurrencies(coinsInfo)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}