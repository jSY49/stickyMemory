package com.example.stickymemory.viewModel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.stickymemory.Repository.DdayRepository
import com.example.stickymemory.database.AppDatabase_dday
import com.example.stickymemory.dataclasses.Dday
import com.example.stickymemory.dataclasses.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DdayViewModel(application: Application) : ViewModel() {
     //뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
     val readAllData : LiveData<List<Dday>>
    private val repository : DdayRepository

    init {
        val ddayDao = AppDatabase_dday.getDatabase(application)!!.ddayDao()
        repository = DdayRepository(ddayDao)
        readAllData = repository.readAllData
    }

    fun addDday(dday : Dday){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDdayList(dday)
        }
    }
    fun updateDday(dday : Dday){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDday(dday)
        }
    }
    fun deleteDday(dday : Dday){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDday(dday)
        }
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        @NonNull
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DdayViewModel(application) as T
        }
    }
}