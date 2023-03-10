package com.example.stickymemory

import android.app.Application
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stickymemory.dataclasses.Memo
import com.example.stickymemory.viewModel.MemoViewModel

@Composable
fun MemoUISet(application: Application, vm: MemoViewModel = viewModel(factory = MemoViewModel.Factory(application))){

    val TAG = object{}.javaClass.enclosingClass.name

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var list: List<Memo>  by rememberSaveable{ mutableStateOf(listOf()) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }

    fun deleteMemo(i: Int) {
        list = list.toMutableList().also { vm.deleteMemo(list[i]) }
    }

    var deleteItem by remember { mutableStateOf(0) }

    MemoList(
        memos = list,
        onDelete = {deleteItem = it
            setShowDialog(true)}
    )
    DeleteDialog(showDialog, setShowDialog) {
        deleteMemo(deleteItem)
    }

    vm.readAllData.observe(lifecycleOwner, Observer {
        it.let {
            list = it
        }

    })

}