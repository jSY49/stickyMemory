package com.example.stickymemory.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todolist")
data class Todo(
    @ColumnInfo(name="date") var date: String?,
    @ColumnInfo(name="todoThing") var todoThing: String,
    @ColumnInfo(name="check") var check: Boolean?,
    @PrimaryKey(autoGenerate = true)var idx: Long = 0
): Serializable

