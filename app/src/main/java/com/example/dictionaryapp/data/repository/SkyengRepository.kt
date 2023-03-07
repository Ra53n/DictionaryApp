package com.example.dictionaryapp.data.repository

import com.example.dictionaryapp.domain.model.SearchEntity
import io.reactivex.Observable

interface SkyengRepository {

    fun searchWord(word: String): Observable<SearchEntity>
}