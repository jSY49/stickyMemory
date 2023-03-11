package com.example.stickymemory.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stickymemory.dataclasses.Dday
import com.example.stickymemory.dataclasses.Memo
import com.example.stickymemory.dataclasses.Todo

@Dao
interface MemoDao {

    @Query("SELECT * FROM memolist")
    fun getAll(): LiveData<List<Memo>>

    @Insert
    fun insertMemo(vararg memo: Memo)

    @Update
    fun updateMemo(memo :Memo)

    @Delete
    fun delete(memo: Memo)
}