package com.github.gunin_igor75.crypto_app.domain.usecase

import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository

class GetCoinInfoListUseCase(private val coinRepository: CoinRepository) {

    operator fun invoke() = coinRepository.getCoinInfoList()
}