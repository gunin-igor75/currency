package com.github.gunin_igor75.crypto_app.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM currency ORDER BY lastUpdate DESC")
    fun getAllCurrency(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM currency WHERE fromSymbol =:fSym LIMIT 1")
    fun getCurrencyByName(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCurrencies(currencies: List<CoinInfoDbModel>)
}