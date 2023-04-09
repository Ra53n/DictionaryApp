package com.example.dictionaryapp.data.repository

import com.example.dictionaryapp.data.room.HistoryDao
import com.example.dictionaryapp.data.room.HistoryRoomEntity

class HistoryRepositoryImpl(private val historyDao: HistoryDao) : HistoryRepository {
    override suspend fun addWordToHistory(word: String, description: String) {
        historyDao.insert(HistoryRoomEntity(word, description))
    }

    override suspend fun getAllSearchedWords(): List<HistoryRoomEntity> {
        return historyDao.all()
    }
}