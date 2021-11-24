package com.amaizzzing.sobes4

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun Date.millisToTimeString(dateInMillis: Long) =
    SimpleDateFormat("HH:mm").format(Date(dateInMillis))

fun Date.atEndOfDay(date: Long): Long {
    val calendar = Calendar.getInstance()
    calendar.time = Date(date)
    calendar[Calendar.HOUR_OF_DAY] = 23
    calendar[Calendar.MINUTE] = 59
    calendar[Calendar.SECOND] = 59
    calendar[Calendar.MILLISECOND] = 999
    return calendar.time.time
}

fun Date.atStartOfDay(date: Long): Long {
    val calendar = Calendar.getInstance()
    calendar.time = Date(date)
    calendar[Calendar.HOUR_OF_DAY] = 0
    calendar[Calendar.MINUTE] = 0
    calendar[Calendar.SECOND] = 0
    calendar[Calendar.MILLISECOND] = 0
    return calendar.time.time
}

fun Int.toBoolean() = this != 0

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}