package com.example.main_feature.domain.interactor

import com.example.main_feature.domain.model.SearchEntity
import com.example.main_feature.domain.repository.SkyengRepository

class MainInteractor(private val repository: SkyengRepository) {

    suspend fun searchWord(word: String): SearchEntity {
        return repository.searchWord(word)
    }
}