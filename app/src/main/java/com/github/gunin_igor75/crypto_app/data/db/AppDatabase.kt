package com.github.gunin_igor75.crypto_app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinInfoDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "main.db"
        private val LOOK = Any()
        private var database: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            database?.let { return it }
            synchronized(LOOK) {
                val instance: AppDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                database = instance
                return instance
            }
        }
    }

    abstract fun appDao(): AppDao
}