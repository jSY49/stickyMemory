package com.example.stickymemory.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stickymemory.dataclasses.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todolist")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insertTodo(vararg todos: Todo)

    @Update
    fun update(todo :Todo)

    @Delete
    fun delete(todo: Todo)
}