package com.example.dictionaryapp.data.api

import com.example.dictionaryapp.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {

    @GET("words/search")
    suspend fun searchWord(@Query("search") word: String): List<SearchResponse>
}