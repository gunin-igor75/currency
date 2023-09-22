package com.github.gunin_igor75.crypto_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.gunin_igor75.crypto_app.R
import com.github.gunin_igor75.crypto_app.databinding.ItemCoinBinding
import com.github.gunin_igor75.crypto_app.pojo.InfoCurrency
import com.squareup.picasso.Picasso

class CoinAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    var coins: List<InfoCurrency> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var coinOnClickListener: CoinOnClickListener? = null

    inner class CoinViewHolder(private val itemCoinBinding: ItemCoinBinding) :
        RecyclerView.ViewHolder(itemCoinBinding.root) {
        val textViewCoinName = itemCoinBinding.textViewCoinName
        val textViewCoinCost = itemCoinBinding.textViewCoinCost
        val textViewCoinUpdateTime = itemCoinBinding.textViewCoinUpdateTime
        val imageViewCoin = itemCoinBinding.imageViewCoin
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = ItemCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinViewHolder(view)
    }

    override fun getItemCount() = coins.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]
        val templateName = context.resources.getString(R.string.coin_name)
        val templateUpdate = context.resources.getString(R.string.coin_update)
        with(holder) {
            textViewCoinName.text = String.format(templateName, coin.fromSymbol, coin.toSymbol)
            textViewCoinUpdateTime.text = String.format(templateUpdate, coin.secondToDate())
            textViewCoinCost.text = coin.price.toString()
            Picasso.get().load(coin.getFullPathImage()).into(imageViewCoin)

            itemView.setOnClickListener {
                coinOnClickListener?.onCoinClick(coin)
            }
        }
    }

    interface CoinOnClickListener {
        fun onCoinClick(coin: InfoCurrency)
    }
}
















