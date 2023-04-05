package com.example.dictionaryapp.data.repository

import com.example.dictionaryapp.data.room.HistoryRoomEntity

interface HistoryRepository {

    suspend fun addWordToHistory(word: String, description: String)

    suspend fun getAllSearchedWords(): List<HistoryRoomEntity>
}