package com.example.main_feature.data.repository

import com.example.main_feature.data.api.SkyengApi
import com.example.main_feature.data.mapper.SearchResponseToEntityMapper
import com.example.main_feature.domain.model.SearchEntity
import com.example.main_feature.domain.repository.SkyengRepository

class SkyengRepositoryImpl(
    private val api: SkyengApi,
    private val mapper: SearchResponseToEntityMapper
) : SkyengRepository {

    override suspend fun searchWord(word: String): SearchEntity {
        return mapper.map(api.searchWord(word).first())
    }
}