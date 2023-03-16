package com.jaysdevapp.stickymemory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jaysdevapp.stickymemory.Dao.ddayDao
import com.jaysdevapp.stickymemory.dataclasses.Dday

@Database(entities = [Dday::class], version = 1)
abstract class AppDatabase_dday :RoomDatabase(){
    abstract fun ddayDao() : ddayDao

    companion object{
        @Volatile
        private var instance : AppDatabase_dday? = null
        fun getDatabase(context : Context) : AppDatabase_dday? {
            if(instance == null){
                synchronized(AppDatabase_dday::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase_dday::class.java,
                        "Ddaylist_database"
                    ).build()
                }
            }
            return instance
        }
    }
}