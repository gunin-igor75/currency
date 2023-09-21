package com.github.gunin_igor75.crypto_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.gunin_igor75.crypto_app.databinding.ItemCoinBinding
import com.github.gunin_igor75.crypto_app.pojo.InfoCurrency
import com.squareup.picasso.Picasso

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    var coins = ArrayList<InfoCurrency>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


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
        with(holder) {
            textViewCoinName.text = coin.fromSymbol + " / " + coin.toSymbol
            textViewCoinCost.text = coin.price.toString()
            textViewCoinUpdateTime.text = coin.secondToDate()
            Picasso.get().load(coin.getFullPathImage()).into(imageViewCoin)
        }
    }
}
















