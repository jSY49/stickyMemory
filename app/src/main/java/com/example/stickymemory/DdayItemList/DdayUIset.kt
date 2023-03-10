package com.example.stickymemory

import android.app.Application
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stickymemory.dataclasses.Dday
import com.example.stickymemory.viewModel.DdayViewModel

@Composable
fun DdayUISet(application: Application, vm: DdayViewModel = viewModel(factory = DdayViewModel.Factory(application))){

    val TAG = object{}.javaClass.enclosingClass.name

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var list: List<Dday>  by rememberSaveable{ mutableStateOf(listOf()) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }

    fun deleteDday(i: Int) {
        list = list.toMutableList().also { vm.deleteDday(list[i]) }
    }

    var deleteItem by remember { mutableStateOf(0) }

    DdayList(
        ddays = list,
        onDelete = {deleteItem = it
            setShowDialog(true)}
    )
    DeleteDialog(showDialog, setShowDialog) {
        deleteDday(deleteItem)
    }

    vm.readAllData.observe(lifecycleOwner, Observer {
        it.let {
            list = it
        }

    })

}