package com.example.dictionaryapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.data.repository.HistoryRepository
import com.example.dictionaryapp.presentation.mapper.HistoryUiModelMapper
import com.example.dictionaryapp.presentation.model.HistoryUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyRepository: HistoryRepository,
    private val mapper: HistoryUiModelMapper
) : HistoryViewModelContract.ViewModel() {
    override val history: MutableLiveData<List<HistoryUiModel>> = MutableLiveData()
    override val error: MutableLiveData<String> = MutableLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        error.postValue("Error")
    }

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            history.postValue(historyRepository.getAllSearchedWords().map { mapper.map(it) })
        }
    }
}