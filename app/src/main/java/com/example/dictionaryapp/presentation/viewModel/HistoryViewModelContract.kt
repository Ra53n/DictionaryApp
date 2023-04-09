package com.example.dictionaryapp.presentation.viewModel

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.presentation.model.HistoryUiModel

interface HistoryViewModelContract {

    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val history: LiveData<List<HistoryUiModel>>

        abstract val error: LiveData<String>

    }
}