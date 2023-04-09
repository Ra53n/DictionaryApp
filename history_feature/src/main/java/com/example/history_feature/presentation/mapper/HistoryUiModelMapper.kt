package com.example.history_feature.presentation.mapper

import com.example.history_feature.data.room.HistoryRoomEntity
import com.example.history_feature.presentation.model.HistoryUiModel

class HistoryUiModelMapper {
    fun map(historyRoomEntity: HistoryRoomEntity): HistoryUiModel {
        return with(historyRoomEntity) {
            HistoryUiModel(word = word, description = description.orEmpty())
        }
    }
}