package com.example.history_feature.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.core.presentation.utils.viewById
import com.example.history_feature.R
import com.example.history_feature.presentation.viewModel.HistoryViewModelContract
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

class HistoryActivity : AppCompatActivity(), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private val viewModel: HistoryViewModelContract.ViewModel by viewModel()

    private val historyRecyclerview by viewById<RecyclerView>(R.id.recycler)

    private val adapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        initViews()
        observeData()
    }

    private fun initViews() {
        historyRecyclerview.adapter = adapter
    }

    private fun observeData() {
        viewModel.history.observe(this) {
            adapter.setItems(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, getString(R.string.error_recieved), Toast.LENGTH_SHORT).show()
        }
    }
}