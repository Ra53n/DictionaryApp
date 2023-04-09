package com.example.dictionaryapp.presentation.viewModel

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.presentation.model.MeaningUiModel

interface MainViewModelContract {
    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val meanings: LiveData<List<MeaningUiModel>>

        abstract val error: LiveData<String>

        abstract fun searchWord(word: String)
    }
}