package com.github.gunin_igor75.crypto_app.domain.usecase

import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke() = coinRepository.loadData()
}