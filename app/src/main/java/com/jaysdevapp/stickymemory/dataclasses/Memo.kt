package com.jaysdevapp.stickymemory.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Memolist")
data class Memo(
    @ColumnInfo(name="MemoThing") var memoThing: String,
    @PrimaryKey(autoGenerate = true)var idx: Long = 0
): Serializable