package com.example.stickymemory

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stickymemory.dataclasses.Todo
import com.example.stickymemory.viewModel.todoViewModel

@Composable
fun todoUISet(application: Application, vm: todoViewModel = viewModel(factory = todoViewModel.Factory(application))){

    val TAG = object{}.javaClass.enclosingClass.name

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var list: List<Todo>  by rememberSaveable{ mutableStateOf(listOf()) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }

    fun editTodo(i: Int, todo: Todo) {
        list = list.toMutableList().also {
            Log.d(TAG,"${todo},${todo.idx} | ${it[i]},${it[i].idx} | ${list[i]},${list[i].idx} ")
           it[i]=todo
            Log.d(TAG,"${todo},${todo.idx} | ${it[i]},${it[i].idx} | ${list[i]},${list[i].idx} ")
            vm.updatetodo(it[i])

        }
    }
    fun deleteTodo(i: Int) {
        list = list.toMutableList().also { vm.deleteTodo(list[i]) }
    }

    var deleteItem by remember { mutableStateOf(0) }

    TodoList(
        todos = list,
        onChange = { i , todo -> editTodo(i, todo) },
        onDelete = {deleteItem = it
            setShowDialog(true)}
    )
    DeleteDialog(showDialog, setShowDialog) {
        deleteTodo(deleteItem)
    }

    vm.readAllData.observe(lifecycleOwner, Observer {
        it.let {
            list = it
        }

    })

}