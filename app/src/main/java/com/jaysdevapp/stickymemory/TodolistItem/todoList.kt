package com.jaysdevapp.stickymemory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaysdevapp.stickymemory.dataclasses.Todo

@Composable
fun TodoList(
    todos: List<Todo>,
    onChange: (i: Int, todo: Todo) -> Unit,
    onDelete: (i: Int) -> Unit,
    onEdit: (i:Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        itemsIndexed(items = todos) { i, todo ->
            TodoItem(
                item = todo,
                onChange = { onChange(i, it) },
                onDelete = { onDelete(i) },
                onEdit = { onEdit(i) }
            )
        }
    }
}