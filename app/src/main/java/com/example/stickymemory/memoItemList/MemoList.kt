package com.example.stickymemory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stickymemory.dataclasses.Memo

@Composable
fun MemoList(
    memos: List<Memo>,
    onDelete: (i: Int) -> Unit,
    onEdit: (i:Int) -> Unit
) {
    LazyColumn( modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        itemsIndexed(items = memos) { i, memo ->
            MemoItem(
                item = memo,
                onDelete = { onDelete(i) },
                onEdit = { onEdit(i) }
            )
        }
    }
}