package com.example.dictionaryapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryapp.data.model.AppState
import com.example.dictionaryapp.presentation.presenter.Presenter

abstract class BaseActivity<T : Presenter<*>> : AppCompatActivity(), View {

    abstract val presenter: T

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    abstract fun renderData(state: AppState)
}