package com.jaysdevapp.stickymemory.viewModel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jaysdevapp.stickymemory.Repository.MemoRepository
import com.jaysdevapp.stickymemory.database.AppDatabase_Memo
import com.jaysdevapp.stickymemory.dataclasses.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MemoViewModel(application: Application) : ViewModel() {
     //뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
     val readAllData : LiveData<List<Memo>>
    private val repository : MemoRepository

    init {
        val memoDao = AppDatabase_Memo.getDatabase(application)!!.memoDao()
        repository = MemoRepository(memoDao)
        readAllData = repository.readAllData
    }

    fun addMemo(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMemoList(memo)
        }
    }
    fun updateMemo(memo :Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMemo(memo)
        }
    }
    fun deleteMemo(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMemo(memo)
        }
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        @NonNull
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MemoViewModel(application) as T
        }
    }
}