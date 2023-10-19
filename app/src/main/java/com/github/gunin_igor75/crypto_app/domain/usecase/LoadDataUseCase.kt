package com.github.gunin_igor75.crypto_app.domain.usecase

import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository

class LoadDataUseCase(private val coinRepository: CoinRepository) {

    suspend operator fun invoke() = coinRepository.loadData()
}