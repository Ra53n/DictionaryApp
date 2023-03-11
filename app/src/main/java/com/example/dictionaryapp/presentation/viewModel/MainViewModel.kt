package com.example.dictionaryapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.data.repository.SkyengRepository
import com.example.dictionaryapp.presentation.SingleLiveEvent
import com.example.dictionaryapp.presentation.mapper.MeaningUiModelMapper
import com.example.dictionaryapp.presentation.model.MeaningUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val repo: SkyengRepository,
    private val mapper: MeaningUiModelMapper
) : MainViewModelContract.ViewModel() {

    override val meanings: MutableLiveData<List<MeaningUiModel>> = MutableLiveData()
    override val error: SingleLiveEvent<String> = SingleLiveEvent()

    override fun searchWord(word: String) {
        repo.searchWord(word)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { meanings.postValue(mapper.map(it.meanings)) },
                { error.postValue("Error") }
            )
            .addViewLifecycle()
    }
}