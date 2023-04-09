package com.example.history_feature.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryRoomEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}