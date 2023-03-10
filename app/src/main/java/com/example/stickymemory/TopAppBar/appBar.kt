package com.example.stickymemory

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun actions(application: Application): @Composable RowScope.() -> Unit = {
    var expanded by remember { mutableStateOf(false) }
    val listItems = arrayOf("To-do", "D-day", "Memo")
    var selected by remember { mutableStateOf(0) }

    var showTodoDialog by remember { mutableStateOf(false) }
    var showDdayDialog by remember { mutableStateOf(false) }
    var showMemoDialog by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
        }
    ) {
        Icon(Icons.Filled.Add, "Add")
        Box(
            contentAlignment = Alignment.Center
        ) {
            // 3 vertical dots icon
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Open Options"
                )
            }

            if (showTodoDialog) {
                todo_ui(setShowDialog = { showTodoDialog = it },application)
            }
            if (showDdayDialog) {
                dday_ui(value = "", setShowDialog = { showDdayDialog = it },application)
            }
            if (showMemoDialog) {
                memo_ui(value = "", setShowDialog = { showMemoDialog = it },application)
            }

            // drop down menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                offset = DpOffset(10.dp, 0.dp)
            ) {
                listItems.forEachIndexed { itemIndex, itemValue ->
                    DropdownMenuItem(
                        onClick = {
                            selected = itemIndex
                            when (selected) {
                                0 -> {
                                    showTodoDialog = true
                                }
                                1 -> {
                                    showDdayDialog = true
                                }
                                2 -> {
                                    showMemoDialog = true
                                }
                                else -> {}
                            }
                            expanded = false

                        }
                    ) {
                        Text(text = itemValue)
                    }
                }
            }
        }

    }
}


@Composable
fun TopBar(
    title: Int,
    actions: @Composable() (RowScope.() -> Unit) = {},
) {
    TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
        Box {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha = 1f),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = stringResource(title),
                    style = MaterialTheme.typography.h6
                )
            }
            Row(
                Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        }
    }
}