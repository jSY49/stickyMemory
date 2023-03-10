package com.example.stickymemory.Repository

import androidx.lifecycle.LiveData
import com.example.stickymemory.Dao.MemoDao
import com.example.stickymemory.dataclasses.Memo
import com.example.stickymemory.dataclasses.Todo

//앱에서 사용하는 데이터와 통신을 하는 역할
class MemoRepository(private val memoDao: MemoDao) {
    val readAllData : LiveData<List<Memo>> =memoDao.getAll()
    suspend fun addMemoList(Memo: Memo){
        memoDao.insertMemo(Memo)
    }
    suspend fun deleteMemo(memo: Memo){
        memoDao.delete(memo)
    }
}