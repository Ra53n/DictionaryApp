package com.example.dictionaryapp.data.model

import java.io.Serializable

data class SearchResponse(
    val id: Long,
    val text: String,
    val meanings: List<MeaningResponse>
) : Serializable

data class MeaningResponse(
    val id: Long,
    val translation: TranslationResponse
) : Serializable

data class TranslationResponse(
    val text: String,
    val note: String? = null
) : Serializable