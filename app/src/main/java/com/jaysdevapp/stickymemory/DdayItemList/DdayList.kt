package com.jaysdevapp.stickymemory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaysdevapp.stickymemory.dataclasses.Dday

@Composable
fun DdayList(
    ddays: List<Dday>,
    onDelete: (i: Int) -> Unit,
    onEdit: (i:Int) -> Unit
) {
    LazyColumn( modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp,0.dp,0.dp,60.dp)  //광고짤리는거 해결안돼서 임시
    ) {
        itemsIndexed(items = ddays) { i, dday ->
            DdayItem(
                item = dday,
                onDelete = { onDelete(i) },
                onEdit = { onEdit(i) }
            )
        }
    }
}