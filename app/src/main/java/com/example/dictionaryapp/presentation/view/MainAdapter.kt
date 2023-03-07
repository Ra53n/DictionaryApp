package com.example.dictionaryapp.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.MeaningAdapterItemBinding
import com.example.dictionaryapp.presentation.model.MeaningUiModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var items: List<MeaningUiModel> = emptyList()


    fun setItems(items: List<MeaningUiModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.meaning_adapter_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val binding = MeaningAdapterItemBinding.bind(itemView)

        fun bind(meaning: MeaningUiModel) {
            binding.translation.text = "Перевод: ${meaning.meaning}"
            if (meaning.note.isNullOrEmpty()) {
                binding.note.visibility = android.view.View.GONE
            } else {
                binding.note.text = "В контексте: ${meaning.note}"
            }
        }
    }
}