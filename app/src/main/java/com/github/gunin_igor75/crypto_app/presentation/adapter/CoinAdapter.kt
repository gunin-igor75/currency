package com.github.gunin_igor75.crypto_app.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.gunin_igor75.crypto_app.R
import com.github.gunin_igor75.crypto_app.databinding.ItemCoinBinding
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinAdapter @Inject constructor(
    private val context: Context
) :
    ListAdapter<CoinInfo, CoinViewHolder>(CoinItemDiffCallback) {

    var coinOnClickListener: CoinOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = ItemCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        val templateName = context.resources.getString(R.string.coin_name)
        val templateUpdate = context.resources.getString(R.string.coin_update)
        with(holder.binding) {
            textViewCoinName.text = String.format(templateName, coin.fromSymbol, coin.toSymbol)
            textViewCoinUpdateTime.text = String.format(templateUpdate, coin.lastUpdate)
            textViewCoinCost.text = coin.price.toString()
            Picasso.get().load(coin.imageUrl).into(imageViewCoin)

            root.setOnClickListener {
                coinOnClickListener?.onCoinClick(coin)
            }
        }
    }

    interface CoinOnClickListener {
        fun onCoinClick(coin: CoinInfo)
    }
}
















