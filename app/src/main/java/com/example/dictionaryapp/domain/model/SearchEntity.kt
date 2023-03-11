package com.example.dictionaryapp.domain.model

class SearchEntity(
    val text: String,
    val meanings: List<MeaningEntity>
) {
    class MeaningEntity(
        val translation: String,
        val note: String?
    )
}