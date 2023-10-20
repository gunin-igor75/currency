package com.github.gunin_igor75.crypto_app.di

import com.github.gunin_igor75.crypto_app.data.workers.ElementWorkerFactory
import com.github.gunin_igor75.crypto_app.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerModelKey(RefreshDataWorker::class)
    @Binds
    fun bindsRefreshDataWorker(worker: RefreshDataWorker.Factory): ElementWorkerFactory
}