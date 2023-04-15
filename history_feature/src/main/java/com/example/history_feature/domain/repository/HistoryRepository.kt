package com.example.history_feature.domain.repository

import com.example.history_feature.data.room.HistoryRoomEntity

interface HistoryRepository {

    suspend fun addWordToHistory(word: String, description: String)

    suspend fun getAllSearchedWords(): List<HistoryRoomEntity>
}