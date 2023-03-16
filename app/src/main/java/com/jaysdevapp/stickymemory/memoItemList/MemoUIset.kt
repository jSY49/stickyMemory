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
import com.jaysdevapp.stickymemory.dataclasses.Memo
import com.jaysdevapp.stickymemory.viewModel.MemoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemoUISet(
    application: Application,
    vm: MemoViewModel = viewModel(factory = MemoViewModel.Factory(application))
) {

    val TAG = object {}.javaClass.enclosingClass.name

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var list: List<Memo> by rememberSaveable { mutableStateOf(listOf()) }
    val (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }
    val (showEditDialog, setShowEditDialog) = rememberSaveable { mutableStateOf(false) }

    fun deleteMemo(i: Int) {
        list = list.toMutableList().also { vm.deleteMemo(list[i]) }
    }

    var Item by remember { mutableStateOf(0) }

    MemoList(
        memos = list,
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
        deleteMemo(Item)
    }

    if(showEditDialog){
        editOrsendDialog_memo(application,list[Item], showEditDialog, setShowEditDialog) {

        }
    }
    vm.readAllData.observe(lifecycleOwner, Observer {
        it.let {
            list = it
        }

    })

}