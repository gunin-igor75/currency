package com.github.gunin_igor75.crypto_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.gunin_igor75.crypto_app.pojo.InfoCurrency

@Dao
interface AppDao {

    @Query("SELECT * FROM currency ORDER BY lastUpdate")
    fun getAllCurrency(): LiveData<List<InfoCurrency>>

    @Query("SELECT * FROM currency WHERE fromSymbol =:fSym LIMIT 1")
    fun getCurrencyByName(fSym: String): LiveData<InfoCurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrencies(currencies: List<InfoCurrency>)
}