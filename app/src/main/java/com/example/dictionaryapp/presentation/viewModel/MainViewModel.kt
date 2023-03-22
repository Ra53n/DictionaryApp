package com.example.dictionaryapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.data.repository.SkyengRepository
import com.example.dictionaryapp.presentation.SingleLiveEvent
import com.example.dictionaryapp.presentation.mapper.MeaningUiModelMapper
import com.example.dictionaryapp.presentation.model.MeaningUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: SkyengRepository,
    private val mapper: MeaningUiModelMapper
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
        }
    }
}