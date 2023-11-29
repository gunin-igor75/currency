package com.github.gunin_igor75.crypto_app.data.workers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.github.gunin_igor75.crypto_app.data.db.AppDao
import com.github.gunin_igor75.crypto_app.data.mapper.CoinMapper
import com.github.gunin_igor75.crypto_app.data.network.retro.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject


class RefreshDataWorker (
    context: Context,
    workerParameters: WorkerParameters,
    private val mapper: CoinMapper,
    private val appDao: AppDao,
    private val apiService: ApiService

) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val coins = apiService.getCoinNameList()
                val namesString = mapper.mapCoinNameListToString(coins)
                val json = apiService.getInfoCurrency(namesString)
                val coinsInfoDto = mapper.mapJsonToList(json)
                val coinsInfo = coinsInfoDto.map { mapper.mapDtoToDbModel(it) }
                appDao.saveCurrencies(coinsInfo)
            } catch (e: Exception) {
                Log.d(TAG, "internet")
            }
            delay(10000)
        }
    }

    class Factory @Inject constructor(
        private val mapper: CoinMapper,
        private val appDao: AppDao,
        private val apiService: ApiService
    ): ElementWorkerFactory{
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                mapper,
                appDao,
                apiService
            )
        }
    }

    companion object {
        private const val TAG = "RefreshDataWorker"
        const val NAME = "worker_load_data"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .setConstraints(makeConstraint())
                .build()
        }

        private fun makeConstraint(): Constraints {
            return Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(requiresBatteryNotLow = true)
                .build()
        }
    }
}