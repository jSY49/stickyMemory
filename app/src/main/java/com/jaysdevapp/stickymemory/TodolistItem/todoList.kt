package com.jaysdevapp.stickymemory

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaysdevapp.stickymemory.dataclasses.Todo

@Composable
fun TodoList(
    todos: List<Todo>,
    onChange: (i: Int, todo: Todo) -> Unit,
    onDelete: (i: Int) -> Unit,
    onEdit: (i:Int) -> Unit
) {
    var nowdate=""

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp,0.dp,0.dp,60.dp)  //광고짤리는거 해결안돼서 임시
    ) {
        itemsIndexed(items = todos) { i, todo ->
            if(i==0){
                nowdate=todo.date.toString()
                Text(text = nowdate, modifier = Modifier.padding(10.dp), fontSize = 15.sp)
            }else{
                if(nowdate!=todo.date){
                    nowdate=todo.date.toString()
                    Text(text = nowdate, modifier = Modifier.padding(10.dp), fontSize = 15.sp)
                }
            }

            TodoItem(
                item = todo,
                onChange = { onChange(i, it) },
                onDelete = { onDelete(i) },
                onEdit = { onEdit(i) }
            )
        }
    }
}