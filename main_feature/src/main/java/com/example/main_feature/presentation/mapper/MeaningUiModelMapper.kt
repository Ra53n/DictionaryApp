package com.example.main_feature.presentation.mapper

import com.example.main_feature.domain.model.SearchEntity
import com.example.main_feature.presentation.model.MeaningUiModel

class MeaningUiModelMapper {
    fun map(meaningEntity: List<SearchEntity.MeaningEntity>): List<MeaningUiModel> {
        return meaningEntity.map {
            val urlIndex = it.previewUrl.indexOf("https")
            MeaningUiModel(
                it.translation,
                it.note,
                it.previewUrl.subSequence(urlIndex, it.previewUrl.length).toString()
            )
        }
    }
}