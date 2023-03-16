package com.jaysdevapp.stickymemory.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jaysdevapp.stickymemory.dataclasses.Memo

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