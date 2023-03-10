package com.example.stickymemory.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist")
data class Todo(
    @ColumnInfo(name="date") val date: String?,
    @ColumnInfo(name="todoThing") val todoThing: String,
    @ColumnInfo(name="check") var check: Boolean?,
    @PrimaryKey(autoGenerate = true)var idx: Long = 0
){
}

