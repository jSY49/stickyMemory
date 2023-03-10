package com.example.stickymemory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stickymemory.Dao.MemoDao
import com.example.stickymemory.dataclasses.Memo

@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase_Memo :RoomDatabase(){
    abstract fun memoDao() : MemoDao

    companion object{
        @Volatile
        private var instance : AppDatabase_Memo? = null
        fun getDatabase(context : Context) : AppDatabase_Memo? {
            if(instance == null){
                synchronized(AppDatabase_Memo::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase_Memo::class.java,
                        "memolist_database"
                    ).build()
                }
            }
            return instance
        }
    }
}