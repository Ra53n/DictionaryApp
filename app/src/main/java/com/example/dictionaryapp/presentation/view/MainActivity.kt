package com.example.dictionaryapp.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.model.AppState
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.presentation.presenter.MainPresenter
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainPresenter>() {
    override val presenter: MainPresenter by inject()

    private val binding: ActivityMainBinding by viewBinding()

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.recycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { presenter.searchWord(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { presenter.searchWord(it) }
                return true
            }

        })
    }

    override fun renderData(state: AppState) {
        when (state) {
            is AppState.Success -> {
                adapter.setItems(state.meanings)
            }
            is AppState.Error -> {
                Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}