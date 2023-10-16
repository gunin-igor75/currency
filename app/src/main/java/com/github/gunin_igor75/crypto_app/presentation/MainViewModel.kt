package com.github.gunin_igor75.crypto_app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.crypto_app.data.repository.CoinInfoRepositoryDbImp
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.github.gunin_igor75.crypto_app.domain.usecase.GetCoinInfoByFSymbolUseCase
import com.github.gunin_igor75.crypto_app.domain.usecase.GetCoinInfoListUseCase
import com.github.gunin_igor75.crypto_app.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val coinRepository = CoinInfoRepositoryDbImp(application)
    private val getCoinInfoByFSymbolUseCase = GetCoinInfoByFSymbolUseCase(coinRepository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(coinRepository)
    private val loadDataUseCase = LoadDataUseCase(coinRepository)


    val currencies = getCoinInfoListUseCase()


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    fun loadDetailsCoin(fSym: String): LiveData<CoinInfo> {
        return getCoinInfoByFSymbolUseCase(fSym)
    }
}