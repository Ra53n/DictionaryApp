package com.example.dictionaryapp.data.repository

import com.example.dictionaryapp.data.api.SkyengApi
import com.example.dictionaryapp.data.mapper.SearchResponseToEntityMapper
import com.example.dictionaryapp.domain.model.SearchEntity
import io.reactivex.Observable

class SkyengRepositoryImpl(
    private val api: SkyengApi,
    private val mapper: SearchResponseToEntityMapper
) : SkyengRepository {

    override fun searchWord(word: String): Observable<SearchEntity> {
        return api.searchWord(word).map { mapper.map(it.first()) }
    }
}