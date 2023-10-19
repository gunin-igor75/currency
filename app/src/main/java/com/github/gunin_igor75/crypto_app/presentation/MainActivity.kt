package com.github.gunin_igor75.crypto_app.presentation


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import com.github.gunin_igor75.crypto_app.R
import com.github.gunin_igor75.crypto_app.databinding.ActivityMainBinding
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.github.gunin_igor75.crypto_app.presentation.adapter.CoinAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: CoinAdapter

    private var fcvCoinDetails: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.currencies.observe(this) { adapter.submitList(it) }
        onclickAdapter()
    }


    private fun setupRecyclerView() {
        val recyclerViewCoins = binding.recyclerViewCoins
        recyclerViewCoins.animation = null
        adapter = CoinAdapter(this)
        recyclerViewCoins.adapter = adapter
    }

    private fun onclickAdapter() {
        adapter.coinOnClickListener = object : CoinAdapter.CoinOnClickListener {
            override fun onCoinClick(coin: CoinInfo) {
                if (isOnePaneMode()) {
                    val intent =
                        DetailsCoinActivity.newIntent(this@MainActivity, coin.fromSymbol)
                    startActivity(intent)
                } else {
                    launchFragment(CoinDetailsFragment.newInstanceCoinDetails(coin.fromSymbol))
                }
            }
        }
    }

    private fun isOnePaneMode() = binding.fcvCoinDetails == null

    private fun launchFragment(fragment: CoinDetailsFragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_coin_details, fragment)
            .addToBackStack(null)
            .commit()
    }
}