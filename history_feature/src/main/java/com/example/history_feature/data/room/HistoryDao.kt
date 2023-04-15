package com.example.history_feature.data.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryRoomEntity")
    suspend fun all(): List<HistoryRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HistoryRoomEntity)

    @Update
    suspend fun update(entity: HistoryRoomEntity)

    @Delete
    suspend fun delete(entity: HistoryRoomEntity)
}
