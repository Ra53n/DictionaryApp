package com.example.dictionaryapp.presentation.mapper

import com.example.dictionaryapp.domain.model.SearchEntity
import com.example.dictionaryapp.presentation.model.MeaningUiModel

class MeaningUiModelMapper {
    fun map(meaningEntity: List<SearchEntity.MeaningEntity>): List<MeaningUiModel> {
        return meaningEntity.map { MeaningUiModel(it.translation, it.note) }
    }
}