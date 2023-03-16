package com.jaysdevapp.stickymemory.viewModel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.*
import com.jaysdevapp.stickymemory.Repository.TodoRepository
import com.jaysdevapp.stickymemory.database.AppDatabase
import com.jaysdevapp.stickymemory.dataclasses.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class todoViewModel(application: Application) : ViewModel() {
     //뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
     val readAllData : LiveData<List<Todo>>
    private val repository : TodoRepository

    init {
        val todoDao = AppDatabase.getDatabase(application)!!.todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todo : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodoList(todo)
        }
    }

    fun updatetodo(todo : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }



    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        @NonNull
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return todoViewModel(application) as T
        }
    }
}