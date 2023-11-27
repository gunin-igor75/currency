package com.github.gunin_igor75.crypto_app.di

import androidx.lifecycle.ViewModel
import com.github.gunin_igor75.crypto_app.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel
}