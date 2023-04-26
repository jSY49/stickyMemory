package com.jaysdevapp.stickymemory

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun calDate(date: String?): String {

    if (!date.isNullOrBlank()) {
        var dday_str = date!!.split("-").toMutableList()
        Log.d("cal", "$dday_str")
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        for (i in 1..2) {
            if (dday_str[i].toInt() < 10) {
                dday_str[i] = "0" + dday_str[i]
            }
            dday_str[i] = dday_str[i].replace(" ", "")
        }
        Log.d("cal date", dday_str.toString())
        val endDate = dateFormat.parse(dday_str[0] + dday_str[1] + dday_str[2]).time

        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time
        val res = ((endDate - today) / (24 * 60 * 60 * 1000))
        return if (res == 0L) {
            "D-Day"
        } else if (res > 0) {
            "D-" + res.toString()
        } else {
            "D+" + (res * (-1)).toString()
        }

    }
    else{
        return "none"
    }
}

