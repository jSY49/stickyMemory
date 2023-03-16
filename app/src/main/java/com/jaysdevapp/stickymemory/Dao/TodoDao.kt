package com.jaysdevapp.stickymemory.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jaysdevapp.stickymemory.dataclasses.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todolist ORDER BY date ASC")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insertTodo(vararg todos: Todo)

    @Update
    fun update(todo :Todo)

    @Delete
    fun delete(todo: Todo)
}