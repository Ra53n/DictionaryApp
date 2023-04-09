package com.example.main_feature.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main_feature.R
import com.example.main_feature.databinding.AdapterItemBinding
import com.example.main_feature.presentation.model.MeaningUiModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var items: List<MeaningUiModel> = emptyList()


    fun setItems(items: List<MeaningUiModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdapterItemBinding.bind(itemView)

        fun bind(meaning: MeaningUiModel) {
            binding.translation.text = "Перевод: ${meaning.meaning}"
            if (meaning.note.isNullOrEmpty()) {
                binding.note.visibility = android.view.View.GONE
            } else {
                binding.note.text = "В контексте: ${meaning.note}"
            }
            Glide.with(binding.preview.context)
                .load(meaning.imageUrl)
                .centerCrop().into(binding.preview)
        }
    }
}