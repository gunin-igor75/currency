package com.github.gunin_igor75.crypto_app.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun converterTime(timeInt: Int): String {
    val date = Date(timeInt.toLong() * 1000)
    val pattern = "HH:mm:ss"
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    formatter.timeZone = TimeZone.getDefault()
    return formatter.format(date)
}