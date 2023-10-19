package com.github.gunin_igor75.crypto_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.github.gunin_igor75.crypto_app.domain.usecase.GetCoinInfoByFSymbolUseCase
import com.github.gunin_igor75.crypto_app.domain.usecase.GetCoinInfoListUseCase
import com.github.gunin_igor75.crypto_app.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getCoinInfoByFSymbolUseCase: GetCoinInfoByFSymbolUseCase,
    getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {


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