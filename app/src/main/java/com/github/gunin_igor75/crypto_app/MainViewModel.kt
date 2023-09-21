package com.github.gunin_igor75.crypto_app

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.github.gunin_igor75.crypto_app.api.ApiFactory
import com.github.gunin_igor75.crypto_app.database.AppDatabase
import com.github.gunin_igor75.crypto_app.pojo.ContainerCurrency
import com.github.gunin_igor75.crypto_app.pojo.InfoCurrency
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

private const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private val appDao = AppDatabase.getInstance(application).appDao()

    val currencies = appDao.getAllCurrency()

    fun loadData() {
        val disposable = ApiFactory.apiService.getDataContainer()
            .map { it.data?.map { datum -> datum.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getInfoCurrency(fSymS = it) }
            .map { jsonToInfoCurrencies(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    appDao.saveCurrencies(it)
                }, {
                    Log.d(TAG, it.message.toString())
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun jsonToInfoCurrencies(data: ContainerCurrency): List<InfoCurrency> {
        val results = ArrayList<InfoCurrency>()
        val jsonObject = data.containerCurrencyJson ?: return results
        for (key in jsonObject.keySet()) {
            val currencyKeys = jsonObject.getAsJsonObject(key)
            for (currencyKey in currencyKeys.keySet()) {
                val currency = Gson().fromJson(
                    currencyKeys.getAsJsonObject(currencyKey),
                    InfoCurrency::class.java
                )
                results.add(currency)
            }
        }
        return results
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}