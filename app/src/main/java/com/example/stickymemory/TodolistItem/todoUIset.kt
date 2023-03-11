package com.example.stickymemory

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stickymemory.dataclasses.Todo
import com.example.stickymemory.viewModel.todoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun todoUISet(
    application: Application,
    vm: todoViewModel = viewModel(factory = todoViewModel.Factory(application))
) {

    val TAG = object {}.javaClass.enclosingClass.name

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var list: List<Todo> by rememberSaveable { mutableStateOf(listOf()) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }
    val (showEditDialog, setShowEditDialog) = rememberSaveable { mutableStateOf(false) }

    fun editTodo(i: Int, todo: Todo) {
        list = list.toMutableList().also {
            it[i] = todo
            vm.updatetodo(it[i])
        }
    }

    fun deleteTodo(i: Int) {
        list = list.toMutableList().also { vm.deleteTodo(list[i]) }
    }

    var Item by remember { mutableStateOf(0) }

    TodoList(
        todos = list,
        onChange = { i, todo -> editTodo(i, todo) },
        onDelete = {
            Item = it
            setShowDialog(true)
        },
        onEdit = {
            Item = it
            setShowEditDialog(true)
        }
    )
    DeleteDialog(showDialog, setShowDialog) {
        deleteTodo(Item)
    }
    if(showEditDialog){
        editOrsendDialog(application,list[Item], showEditDialog, setShowEditDialog) {

        }
    }


    vm.readAllData.observe(lifecycleOwner, Observer {
        it.let {
            list = it
        }

    })

}