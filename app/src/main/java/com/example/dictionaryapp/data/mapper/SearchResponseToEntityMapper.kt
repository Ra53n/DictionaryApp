package com.example.dictionaryapp.data.mapper

import com.example.dictionaryapp.data.model.SearchResponse
import com.example.dictionaryapp.domain.model.SearchEntity

class SearchResponseToEntityMapper {

    fun map(response: SearchResponse) = with(response) {
        SearchEntity(
            text = text,
            meanings = response.meanings.map {
                SearchEntity.MeaningEntity(
                    translation = it.translation.text,
                    note = it.translation.note,
                    previewUrl = it.imageUrl
                )
            }
        )
    }

}