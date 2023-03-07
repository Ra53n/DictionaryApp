package com.example.dictionaryapp.presentation.presenter

import com.example.dictionaryapp.presentation.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Presenter<T : View> {
    protected var view: View? = null

    private val compositeDisposable = CompositeDisposable()

    fun Disposable.addViewLifecycle() {
        compositeDisposable.add(this)
    }

    open fun attachView(view: View) {
        this.view = view
    }

    open fun detachView() {
        view = null
        compositeDisposable.dispose()
    }
}