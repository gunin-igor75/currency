package com.github.gunin_igor75.crypto_app.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo

object CoinItemDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}