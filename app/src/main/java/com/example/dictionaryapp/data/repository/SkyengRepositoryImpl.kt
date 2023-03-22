package com.example.dictionaryapp.data.repository

import com.example.dictionaryapp.data.api.SkyengApi
import com.example.dictionaryapp.data.mapper.SearchResponseToEntityMapper
import com.example.dictionaryapp.domain.model.SearchEntity

class SkyengRepositoryImpl(
    private val api: SkyengApi,
    private val mapper: SearchResponseToEntityMapper
) : SkyengRepository {

    override suspend fun searchWord(word: String): SearchEntity {
        return mapper.map(api.searchWord(word).first())
    }
}