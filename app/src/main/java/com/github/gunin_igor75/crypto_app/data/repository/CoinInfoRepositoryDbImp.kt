package com.github.gunin_igor75.crypto_app.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.github.gunin_igor75.crypto_app.data.db.AppDao
import com.github.gunin_igor75.crypto_app.data.mapper.CoinMapper
import com.github.gunin_igor75.crypto_app.data.workers.RefreshDataWorker
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository
import javax.inject.Inject

class CoinInfoRepositoryDbImp @Inject constructor(
    private val appDao: AppDao,
    private val mapper: CoinMapper,
    private val context: Context
) : CoinRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return appDao.getAllCurrency().map { mapper.mapDbModelsToPojoList(it) }
    }

    override fun getCoinInfoByName(fSym: String): LiveData<CoinInfo> {
        return appDao.getCurrencyByName(fSym).map { mapper.mapDbModelToPojo(it) }
    }

    override fun loadData() {
        val workerManager = WorkManager.getInstance(context)
        workerManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}