package com.megatrust.demochatgpt.utills

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentTime(): String {
    val currentTime = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    return dateFormat.format(Date(currentTime))
}