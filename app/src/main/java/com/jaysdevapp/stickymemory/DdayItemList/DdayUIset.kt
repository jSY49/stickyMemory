package com.jaysdevapp.stickymemory

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaysdevapp.stickymemory.dataclasses.Dday
import com.jaysdevapp.stickymemory.viewModel.DdayViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DdayUISet(application: Application, vm: DdayViewModel = viewModel(factory = DdayViewModel.Factory(application))){

    val TAG = object{}.javaClass.enclosingClass.name

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var list: List<Dday>  by rememberSaveable{ mutableStateOf(listOf()) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }
    val (showEditDialog, setShowEditDialog) = rememberSaveable { mutableStateOf(false) }

    fun deleteDday(i: Int) {
        list = list.toMutableList().also { vm.deleteDday(list[i]) }
    }

    var Item by remember { mutableStateOf(0) }

    DdayList(
        ddays = list,
        onDelete = {Item = it
            setShowDialog(true)},
        onEdit = {
            Item = it
            setShowEditDialog(true)
        }
    )
    DeleteDialog(showDialog, setShowDialog) {
        deleteDday(Item)
    }
    if(showEditDialog){
        editOrsendDialog_dday(application,list[Item], showEditDialog, setShowEditDialog) {

        }
    }

    vm.readAllData.observe(lifecycleOwner, Observer {
        it.let {
            list = it
        }

    })

}