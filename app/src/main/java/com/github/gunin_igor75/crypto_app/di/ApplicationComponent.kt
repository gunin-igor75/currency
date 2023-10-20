package com.github.gunin_igor75.crypto_app.di

import android.content.Context
import com.github.gunin_igor75.crypto_app.CryptoApp
import com.github.gunin_igor75.crypto_app.presentation.CoinDetailsFragment
import com.github.gunin_igor75.crypto_app.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules =
    [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CoinDetailsFragment)

    fun inject(app: CryptoApp)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}