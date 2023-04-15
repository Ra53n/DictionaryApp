package com.example.main_feature.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.presentation.utils.viewById
import com.example.main_feature.R
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

        fun bind(meaning: MeaningUiModel) {
            val translation by viewById<TextView>(R.id.translation)
            val note by viewById<TextView>(R.id.note)
            val preview by viewById<ImageView>(R.id.preview)

            translation.text = "Перевод: ${meaning.meaning}"

            if (meaning.note.isNullOrEmpty()) {
                note.visibility = android.view.View.GONE
            } else {
                note.text = "В контексте: ${meaning.note}"
            }

            Glide.with(preview.context)
                .load(meaning.imageUrl)
                .centerCrop().into(preview)
        }
    }
}