package com.github.gunin_igor75.crypto_app.data.mapper

import com.github.gunin_igor75.crypto_app.data.db.CoinInfoDbModel
import com.github.gunin_igor75.crypto_app.data.network.ApiFactory
import com.github.gunin_igor75.crypto_app.data.network.dto.CoinInfoDto
import com.github.gunin_igor75.crypto_app.data.network.dto.CoinInfoJsonContainerDto
import com.github.gunin_igor75.crypto_app.data.network.dto.CoinNamesListDto
import com.github.gunin_igor75.crypto_app.domain.pojo.CoinInfo
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastUpdate = dto.lastUpdate,
            highDay = dto.highDay,
            lowDay = dto.lowDay,
            lastMarket = dto.lastMarket,
            imageUrl = dto.imageUrl
        )
    }

    fun mapDbModelToPojo(dbModel: CoinInfoDbModel): CoinInfo {
        return CoinInfo(
            fromSymbol = dbModel.fromSymbol,
            toSymbol = dbModel.toSymbol,
            price = dbModel.price,
            lastUpdate = converterTime(dbModel.lastUpdate),
            highDay = dbModel.highDay,
            lowDay = dbModel.lowDay,
            lastMarket = dbModel.lastMarket,
            imageUrl = ApiFactory.BASE_IMAGE_URL + dbModel.imageUrl
        )
    }

    fun mapDbModelsToPojoList(dbModels: List<CoinInfoDbModel>): List<CoinInfo> {
        return dbModels.map { mapDbModelToPojo(it) }
    }

    fun mapJsonToList(data: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val results = ArrayList<CoinInfoDto>()
        val jsonObject = data.json ?: return results
        for (key in jsonObject.keySet()) {
            val currencyKeys = jsonObject.getAsJsonObject(key)
            for (currencyKey in currencyKeys.keySet()) {
                val currency = Gson().fromJson(
                    currencyKeys.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                results.add(currency)
            }
        }
        return results
    }

    fun mapCoinNameListToString(coinNamesListDto: CoinNamesListDto): String {
        return coinNamesListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",") ?: ""
    }

    private fun converterTime(timeInt: Int): String {
        val date = Date(timeInt.toLong() * 1000)
        val pattern = "HH:mm:ss"
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        formatter.timeZone = TimeZone.getDefault()
        return formatter.format(date)
    }
}