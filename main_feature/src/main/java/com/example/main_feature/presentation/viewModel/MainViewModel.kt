package com.example.main_feature.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.utils.SingleLiveEvent
import com.example.history_feature.domain.interactor.HistoryInteractor
import com.example.main_feature.domain.interactor.MainInteractor
import com.example.main_feature.presentation.mapper.MeaningUiModelMapper
import com.example.main_feature.presentation.model.MeaningUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainInteractor: MainInteractor,
    private val mapper: MeaningUiModelMapper,
    private val historyInteractor: HistoryInteractor
) : MainViewModelContract.ViewModel() {

    override val meanings: MutableLiveData<List<MeaningUiModel>> = MutableLiveData()
    override val error: SingleLiveEvent<String> = SingleLiveEvent()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        error.postValue("Error")
    }

    override fun searchWord(word: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val searchResponse = mainInteractor.searchWord(word)
            meanings.postValue(mapper.map(searchResponse.meanings))
            historyInteractor.addWordToHistory(word, searchResponse.meanings.first().translation)
        }
    }
}