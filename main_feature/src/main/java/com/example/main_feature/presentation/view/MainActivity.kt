package com.example.main_feature.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.presentation.utils.viewById
import com.example.history_feature.presentation.view.HistoryActivity
import com.example.main_feature.R
import com.example.main_feature.presentation.viewModel.MainViewModelContract
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private val viewModel: MainViewModelContract.ViewModel by viewModel()

    private val recyclerView by viewById<RecyclerView>(R.id.recycler)

    private val searchView by viewById<SearchView>(R.id.searchView)

    private val historyFab by viewById<FloatingActionButton>(R.id.historyFab)

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observeData()
    }

    private fun initViews() {
        recyclerView.adapter = adapter
        searchView.setOnQueryTextListener(DebouncingQueryTextListener(lifecycle) { word ->
            word?.let { viewModel.searchWord(it) }
        })
        historyFab.setOnClickListener {
            startActivity(Intent(applicationContext, HistoryActivity::class.java))
        }
    }

    private fun observeData() {
        viewModel.meanings.observe(this) {
            adapter.setItems(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, getString(R.string.error_recieved), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}