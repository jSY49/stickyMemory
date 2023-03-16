package com.jaysdevapp.stickymemory.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jaysdevapp.stickymemory.dataclasses.Dday

@Dao
interface ddayDao {

    @Query("SELECT * FROM ddaylist")
    fun getAll(): LiveData<List<Dday>>

    @Insert
    fun insertDday(vararg dday: Dday)

    @Update
    fun updateDday(dday: Dday)

    @Delete
    fun delete(dday: Dday)
}