package com.example.history_feature.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.history_feature.domain.interactor.HistoryInteractor
import com.example.history_feature.presentation.mapper.HistoryUiModelMapper
import com.example.history_feature.presentation.model.HistoryUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyInteractor: HistoryInteractor,
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
            history.postValue(historyInteractor.getAllSearchedWords().map { mapper.map(it) })
        }
    }
}