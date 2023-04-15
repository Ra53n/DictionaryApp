package com.example.history_feature.domain.interactor

import com.example.history_feature.data.room.HistoryRoomEntity
import com.example.history_feature.domain.repository.HistoryRepository

class HistoryInteractor(private val historyRepository: HistoryRepository) {

    suspend fun addWordToHistory(word: String, description: String) {
        historyRepository.addWordToHistory(word = word, description = description)
    }

    suspend fun getAllSearchedWords(): List<HistoryRoomEntity> {
        return historyRepository.getAllSearchedWords()
    }

}