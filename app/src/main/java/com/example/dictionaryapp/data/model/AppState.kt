package com.example.dictionaryapp.data.model

import com.example.dictionaryapp.presentation.model.MeaningUiModel

sealed class AppState {
    class Success(val meanings: List<MeaningUiModel>) : AppState()
    object Error : AppState()
}