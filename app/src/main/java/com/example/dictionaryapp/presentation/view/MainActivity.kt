package com.example.dictionaryapp.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.presentation.viewModel.MainViewModelContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        binding.searchView.setOnQueryTextListener(DebouncingQueryTextListener(lifecycle) { word ->
            word?.let { viewModel.searchWord(it) }
        })
        binding.historyFab.setOnClickListener {
            startActivity(Intent(applicationContext, HistoryActivity::class.java))
        }
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

class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val onDebouncingQueryTextChange: (String?) -> Unit
) : OnQueryTextListener {
    var debouncePeriod: Long = 500

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncePeriod)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}