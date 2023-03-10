package com.example.stickymemory.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ddaylist")
data class Dday(
    @ColumnInfo(name="date") val date: String?,
    @ColumnInfo(name="ddayThing") val ddayThing: String,
    @PrimaryKey(autoGenerate = true) var idx: Long = 0
): Serializable
