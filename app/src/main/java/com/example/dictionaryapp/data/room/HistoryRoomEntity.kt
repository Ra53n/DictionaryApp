package com.example.dictionaryapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryRoomEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    var word: String, @field:ColumnInfo(name = "description")
    var description: String?
)
