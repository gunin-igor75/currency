package com.github.gunin_igor75.crypto_app

import android.app.Application
import com.github.gunin_igor75.crypto_app.di.DaggerApplicationComponent

class CryptoApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}