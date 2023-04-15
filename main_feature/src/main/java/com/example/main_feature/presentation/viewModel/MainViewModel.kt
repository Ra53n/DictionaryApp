package com.example.main_feature.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.utils.SingleLiveEvent
import com.example.history_feature.domain.repository.HistoryRepository
import com.example.main_feature.domain.repository.SkyengRepository
import com.example.main_feature.presentation.mapper.MeaningUiModelMapper
import com.example.main_feature.presentation.model.MeaningUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: SkyengRepository,
    private val mapper: MeaningUiModelMapper,
    private val historyRepository: HistoryRepository
) : MainViewModelContract.ViewModel() {

    override val meanings: MutableLiveData<List<MeaningUiModel>> = MutableLiveData()
    override val error: SingleLiveEvent<String> = SingleLiveEvent()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        error.postValue("Error")
    }

    override fun searchWord(word: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val searchResponse = repo.searchWord(word)
            meanings.postValue(mapper.map(searchResponse.meanings))
            historyRepository.addWordToHistory(word, searchResponse.meanings.first().translation)
        }
    }
}