package com.github.gunin_igor75.crypto_app.di

import android.content.Context
import com.github.gunin_igor75.crypto_app.data.db.AppDao
import com.github.gunin_igor75.crypto_app.data.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesDatabase(context: Context): AppDao {
        return AppDatabase.getInstance(context).appDao()
    }
}