package com.example.dictionaryapp.data.repository

import com.example.dictionaryapp.domain.model.SearchEntity

interface SkyengRepository {

    suspend fun searchWord(word: String): SearchEntity
}