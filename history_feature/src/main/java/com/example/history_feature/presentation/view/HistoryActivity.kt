package com.example.history_feature.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.history_feature.R
import com.example.history_feature.databinding.HistoryActivityBinding
import com.example.history_feature.presentation.viewModel.HistoryViewModelContract
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {
    private val viewModel: HistoryViewModelContract.ViewModel by viewModel()

    private val binding: HistoryActivityBinding by viewBinding()

    private val adapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        initViews()
        observeData()
    }

    private fun initViews() {
        binding.recycler.adapter = adapter
    }

    private fun observeData() {
        viewModel.history.observe(this) {
            adapter.setItems(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Произошла ошибка!", Toast.LENGTH_SHORT).show()
        }
    }
}