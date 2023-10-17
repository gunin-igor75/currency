package com.github.gunin_igor75.crypto_app.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.gunin_igor75.crypto_app.R


class DetailsCoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_coin)

        val fSym = intent.getStringExtra(EXTRA_SYMBOL)
        if (fSym == null) {
            finish()
            return
        }
        if (savedInstanceState == null) {
            launchMode(fSym)
        }
    }

    private fun launchMode(fSym: String) {
        val fragment = CoinDetailsFragment.newInstanceCoinDetails(fSym)
        supportFragmentManager.beginTransaction()
            .replace(R.id.coin_details_container, fragment)
            .commit()
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