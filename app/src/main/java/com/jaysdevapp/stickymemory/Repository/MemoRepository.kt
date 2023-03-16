package com.jaysdevapp.stickymemory.Repository

import androidx.lifecycle.LiveData
import com.jaysdevapp.stickymemory.Dao.MemoDao
import com.jaysdevapp.stickymemory.dataclasses.Memo

//앱에서 사용하는 데이터와 통신을 하는 역할
class MemoRepository(private val memoDao: MemoDao) {
    val readAllData : LiveData<List<Memo>> =memoDao.getAll()
    suspend fun addMemoList(Memo: Memo){
        memoDao.insertMemo(Memo)
    }
    suspend fun updateMemo(memo: Memo){
        memoDao.updateMemo(memo)
    }
    suspend fun deleteMemo(memo: Memo){
        memoDao.delete(memo)
    }
}