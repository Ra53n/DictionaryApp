package com.example.dictionaryapp.presentation.mapper

import com.example.dictionaryapp.data.room.HistoryRoomEntity
import com.example.dictionaryapp.presentation.model.HistoryUiModel

class HistoryUiModelMapper {
    fun map(historyRoomEntity: HistoryRoomEntity): HistoryUiModel {
        return with(historyRoomEntity) {
            HistoryUiModel(word = word, description = description.orEmpty())
        }
    }
}