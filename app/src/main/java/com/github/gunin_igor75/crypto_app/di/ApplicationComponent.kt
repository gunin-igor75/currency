package com.github.gunin_igor75.crypto_app.di

import android.content.Context
import com.github.gunin_igor75.crypto_app.presentation.CoinDetailsFragment
import com.github.gunin_igor75.crypto_app.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface ApplicationComponent {


    fun inject(activity: MainActivity)

    fun inject(fragment: CoinDetailsFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}