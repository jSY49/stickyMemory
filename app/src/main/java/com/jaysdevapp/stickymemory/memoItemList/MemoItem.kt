package com.jaysdevapp.stickymemory

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.jaysdevapp.stickymemory.dataclasses.Memo


@Composable
fun MemoItem(item: Memo, onDelete: () -> Unit, onEdit: () -> Unit) {

    val colorCode = listOf("#FF81D4FA","#FFFFF9C4","#FFD1C4E9")
    val colorcode = colorCode.get(item.colorNum).toColorInt()

    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 10.dp)
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures(onLongPress = { onDelete() }, onTap = { onEdit() })
        }
    ) {
        Row(modifier = Modifier
            .background(color = Color(colorcode))
            .padding(16.dp)) {
            Icon(
                painter = painterResource(R.drawable.baseline_circle_24),
                contentDescription = "Content description for visually impaired",
                modifier = Modifier
                    .width(5.dp)
                    .height(5.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = item.memoTitle,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .padding(end = 8.dp)
            )
        }
    }
}
