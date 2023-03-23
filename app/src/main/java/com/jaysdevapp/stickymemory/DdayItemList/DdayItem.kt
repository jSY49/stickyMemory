package com.jaysdevapp.stickymemory

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jaysdevapp.stickymemory.dataclasses.Dday
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DdayItem(item: Dday, onDelete: () -> Unit, onEdit: () -> Unit) {

    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures(onLongPress = { onDelete() }, onTap = {onEdit()})
        }
    ) {
        Row(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Text(
                text = calDate(item.date),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.4f)
                    .padding(end = 8.dp)
            )
            Text(
                text = "~"+item.date!!,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.5f)
                    .padding(end = 8.dp)

            )
            Text(
                text = item.ddayThing,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.8f)
                    .padding(end = 8.dp)
            )
        }
    }
}

/*
fun calDate(date: String?): String {
    var dday_str = date!!.split("-").toMutableList()
    val dateFormat = SimpleDateFormat("yyyyMMdd")
    for (i in 1..2) {
        if (dday_str[i].toInt() < 10) {
            dday_str[i] = "0" + dday_str[i]
        }
    }
    val endDate = dateFormat.parse(dday_str[0] + dday_str[1] + dday_str[2]).time
    Log.d("cal date", endDate.toString())
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time
    val res = ((endDate - today) / (24 * 60 * 60 * 1000))
    if (res==0L) {
        return "D-Day"
    } else if (res > 0) {
        return "D-" + res.toString()
    } else {
        return "D+" + (res * (-1)).toString()
    }

}

*/
