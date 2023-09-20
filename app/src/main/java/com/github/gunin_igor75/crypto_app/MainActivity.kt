package com.github.gunin_igor75.crypto_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.gunin_igor75.crypto_app.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


import io.reactivex.schedulers.Schedulers

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test();
    }

    private fun test() {
        val disposable = ApiFactory.apiService.getInfoCurrency(fSymS = "BTC")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, it.toString())
            },{
                Log.d(TAG, it.message.toString())
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}