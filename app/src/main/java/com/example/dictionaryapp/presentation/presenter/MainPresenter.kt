package com.example.dictionaryapp.presentation.presenter

import android.util.Log
import com.example.dictionaryapp.data.model.AppState
import com.example.dictionaryapp.data.repository.SkyengRepository
import com.example.dictionaryapp.presentation.mapper.MeaningUiModelMapper
import com.example.dictionaryapp.presentation.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val repo: SkyengRepository,
    private val mapper: MeaningUiModelMapper
) : Presenter<MainActivity>() {

    fun searchWord(word: String) {
        repo.searchWord(word)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { (view as MainActivity).renderData(AppState.Success(mapper.map(it.meanings))) },
                {
                    Log.e("@@@", "searchWord: ", it)
                    (view as MainActivity).renderData(AppState.Error)
                })
            .addViewLifecycle()
    }

}