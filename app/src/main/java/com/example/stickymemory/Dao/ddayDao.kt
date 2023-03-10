package com.example.stickymemory.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stickymemory.dataclasses.Dday
import com.example.stickymemory.dataclasses.Todo

@Dao
interface ddayDao {

    @Query("SELECT * FROM ddaylist")
    fun getAll(): LiveData<List<Dday>>

    @Insert
    fun insertDday(vararg dday: Dday)

    @Delete
    fun delete(dday: Dday)
}