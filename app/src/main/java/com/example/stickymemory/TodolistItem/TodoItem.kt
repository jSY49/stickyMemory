package com.example.stickymemory

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.stickymemory.dataclasses.Todo

@Composable
fun TodoItem(item: Todo, onChange: (todo: Todo) -> Unit, onDelete: () -> Unit) {

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
            if (item.check == false) {
                Text(
                    text = item.date.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(0.5f)
                        .padding(end = 8.dp)
                )
                Text(
                    text = item.todoThing,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Checkbox(
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    checked = item.check!!,
                    onCheckedChange = {
                        onChange(item.copy(check = it))
                    })
            } else {
                //투두 종료
                Text(
                    text = item.date.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(0.8f)
                        .padding(end = 8.dp)
                )
                Text(
                    text = item.todoThing,
                    textDecoration =
                    if (item.check == true) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    },
                    color=
                    if (item.check == true) {
                        Color.Gray
                    } else {
                        Color.Black
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        onChange(item.copy(check = false))
                    }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = Icons.Filled.Done,
                        contentDescription = stringResource(R.string.todo_done)
                    )
                }
            }
        }
    }
}
