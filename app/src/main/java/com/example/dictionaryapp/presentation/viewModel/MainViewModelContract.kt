package com.example.dictionaryapp.presentation.viewModel

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.presentation.model.MeaningUiModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface MainViewModelContract {
    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        private val compositeDisposable = CompositeDisposable()

        abstract val meanings: LiveData<List<MeaningUiModel>>

        abstract val error: LiveData<String>


        abstract fun searchWord(word: String)

        fun Disposable.addViewLifecycle() {
            compositeDisposable.add(this)
        }

        override fun onCleared() {
            compositeDisposable.clear()
            super.onCleared()
        }
    }
}