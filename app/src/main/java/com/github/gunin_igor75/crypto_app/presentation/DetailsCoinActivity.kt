package com.github.gunin_igor75.crypto_app.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.gunin_igor75.crypto_app.databinding.ActivityDetailsCoinBinding
import com.squareup.picasso.Picasso

class DetailsCoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsCoinBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val fSym = intent.getStringExtra(EXTRA_SYMBOL)
        if (fSym == null) {
            finish()
            return
        }
        viewModel.loadDetailsCoin(fSym).observe(this
        ) {
            binding.textViewFSymbol.text = it.fromSymbol
            binding.textViewToSymbol.text = it.toSymbol
            binding.textViewPrice.text = it.price.toString()
            binding.textViewMin.text = it.lowDay.toString()
            binding.textViewMax.text = it.highDay.toString()
            binding.textViewLastDeal.text = it.lastMarket
            binding.textViewLastTime.text = it.lastUpdate
            Picasso.get().load(it.imageUrl).into(binding.imageViewCoinDetails)
        }
    }

    companion object {
        private const val EXTRA_SYMBOL = "fSym"

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, DetailsCoinActivity::class.java)
            intent.putExtra(EXTRA_SYMBOL, fSym)
            return intent
        }
    }
}