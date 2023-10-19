package com.github.gunin_igor75.crypto_app.domain.usecase

import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoByFSymbolUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(fSymbol: String) = coinRepository.getCoinInfoByName(fSymbol)
}