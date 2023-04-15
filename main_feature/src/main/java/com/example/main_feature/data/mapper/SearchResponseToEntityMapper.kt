package com.example.main_feature.data.mapper

import com.example.main_feature.data.model.SearchResponse
import com.example.main_feature.domain.model.SearchEntity

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