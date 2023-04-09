package com.example.dictionaryapp.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.AdapterItemBinding
import com.example.dictionaryapp.presentation.model.HistoryUiModel

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var items: List<HistoryUiModel> = emptyList()


    fun setItems(items: List<HistoryUiModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HistoryViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdapterItemBinding.bind(itemView)

        fun bind(history: HistoryUiModel) {
            binding.translation.text = "Запрос: ${history.word}"
            binding.note.text = "Перевод: ${history.description}"
        }
    }
}