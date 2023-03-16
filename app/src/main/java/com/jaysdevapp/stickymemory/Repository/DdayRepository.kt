package com.jaysdevapp.stickymemory.Repository

import androidx.lifecycle.LiveData
import com.jaysdevapp.stickymemory.Dao.ddayDao
import com.jaysdevapp.stickymemory.dataclasses.Dday

//앱에서 사용하는 데이터와 통신을 하는 역할
class DdayRepository(private val ddayDao: ddayDao) {
    val readAllData : LiveData<List<Dday>> =ddayDao.getAll()
    suspend fun addDdayList(dday: Dday){
        ddayDao.insertDday(dday)
    }
    suspend fun updateDday(dday: Dday){
        ddayDao.updateDday(dday)
    }
    suspend fun deleteDday(dday: Dday){
        ddayDao.delete(dday)
    }

}