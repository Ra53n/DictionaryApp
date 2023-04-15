package com.example.history_feature.data.repository

import com.example.history_feature.data.room.HistoryDao
import com.example.history_feature.data.room.HistoryRoomEntity
import com.example.history_feature.domain.repository.HistoryRepository

class HistoryRepositoryImpl(private val historyDao: HistoryDao) : HistoryRepository {
    override suspend fun addWordToHistory(word: String, description: String) {
        historyDao.insert(HistoryRoomEntity(word, description))
    }

    override suspend fun getAllSearchedWords(): List<HistoryRoomEntity> {
        return historyDao.all()
    }
}