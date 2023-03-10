package com.example.stickymemory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stickymemory.dataclasses.Dday

@Composable
fun DdayList(
    ddays: List<Dday>,
    onDelete: (i: Int) -> Unit
) {
    LazyColumn( modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        itemsIndexed(items = ddays) { i, dday ->
            DdayItem(
                item = dday,
                onDelete = { onDelete(i) }
            )
        }
    }
}