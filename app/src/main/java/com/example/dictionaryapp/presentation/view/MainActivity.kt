package com.example.dictionaryapp.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.presentation.viewModel.MainViewModelContract
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModelContract.ViewModel by viewModel()

    private val binding: ActivityMainBinding by viewBinding()

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observeData()
    }

    private fun initViews() {
        binding.recycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchWord(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchWord(it) }
                return true
            }
        })
    }

    private fun observeData() {
        viewModel.meanings.observe(this) {
            adapter.setItems(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show()
        }
    }
}