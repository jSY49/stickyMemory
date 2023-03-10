package com.example.stickymemory.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Memolist")
data class Memo(
    @ColumnInfo(name="MemoThing") val memoThing: String
){
    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0
}
