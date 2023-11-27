package com.github.gunin_igor75.crypto_app

import android.app.Application
import androidx.work.Configuration
import com.github.gunin_igor75.crypto_app.data.network.ApiService
import com.github.gunin_igor75.crypto_app.data.workers.RefreshWorkerFactory
import com.github.gunin_igor75.crypto_app.di.DaggerApplicationComponent
import javax.inject.Inject

class CryptoApp: Application(), Configuration.Provider {

    @Inject
    lateinit var refreshWorkerFactory: RefreshWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(refreshWorkerFactory).build()

    }
}