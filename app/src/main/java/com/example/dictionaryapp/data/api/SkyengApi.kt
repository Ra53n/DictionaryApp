package com.example.dictionaryapp.data.api

import com.example.dictionaryapp.data.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {

    @GET("words/search")
    fun searchWord(@Query("search") word: String): Observable<List<SearchResponse>>
}