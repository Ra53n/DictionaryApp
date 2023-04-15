package com.example.main_feature.domain.repository

import com.example.main_feature.domain.model.SearchEntity

interface SkyengRepository {

    suspend fun searchWord(word: String): SearchEntity
}