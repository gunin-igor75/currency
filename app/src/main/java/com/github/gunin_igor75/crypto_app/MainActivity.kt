package com.github.gunin_igor75.crypto_app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.gunin_igor75.crypto_app.adapter.CoinAdapter
import com.github.gunin_igor75.crypto_app.databinding.ActivityMainBinding
import com.github.gunin_igor75.crypto_app.pojo.InfoCurrency

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewCoins = binding.recyclerViewCoins
        val adapter = CoinAdapter(this)
        recyclerViewCoins.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.currencies.observe(this) { adapter.coins = it }

        adapter.coinOnClickListener = object : CoinAdapter.CoinOnClickListener {
            override fun onCoinClick(coin: InfoCurrency) {
                val intent = DetailsCoinActivity.newIntent(
                    this@MainActivity,
                    coin.fromSymbol
                )
                startActivity(intent)
            }
        }
    }
}