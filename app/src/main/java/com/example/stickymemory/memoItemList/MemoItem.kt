package com.example.stickymemory

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stickymemory.dataclasses.Memo

@Composable
fun MemoItem(item: Memo, onDelete: () -> Unit) {

    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures(onLongPress = { onDelete() })
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_arrow_right_24),
                contentDescription = "Content description for visually impaired"
            )

            Text(
                text = item.memoThing,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .padding(end = 8.dp)
            )
        }
    }
}
