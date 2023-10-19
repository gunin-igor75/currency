package com.github.gunin_igor75.crypto_app.di

import com.github.gunin_igor75.crypto_app.data.repository.CoinInfoRepositoryDbImp
import com.github.gunin_igor75.crypto_app.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindsCoinInfoRepository(impl: CoinInfoRepositoryDbImp): CoinRepository
}