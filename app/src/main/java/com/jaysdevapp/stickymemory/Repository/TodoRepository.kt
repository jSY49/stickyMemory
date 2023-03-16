package com.jaysdevapp.stickymemory.Repository

import androidx.lifecycle.LiveData
import com.jaysdevapp.stickymemory.Dao.TodoDao
import com.jaysdevapp.stickymemory.dataclasses.Todo

//앱에서 사용하는 데이터와 통신을 하는 역할
class TodoRepository(private val todoDao: TodoDao) {
    val readAllData : LiveData<List<Todo>> =todoDao.getAll()
    suspend fun addTodoList(todo: Todo){
        todoDao.insertTodo(todo)
    }
    suspend fun deleteTodo(todo: Todo){
        todoDao.delete(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.update(todo)
    }
}