package com.fake.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fake.myapplication.R
import com.fake.myapplication.model.NatureItem

class NatureAdapter(
    private val items: List<NatureItem>,
    private val onItemClick: (NatureItem) -> Unit
) : RecyclerView.Adapter<NatureAdapter.NatureViewHolder>() {

    class NatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_image)
        val title: TextView = view.findViewById(R.id.item_title)
        val description: TextView = view.findViewById(R.id.item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NatureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nature, parent, false)
        return NatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: NatureViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.imageResId)
        holder.title.text = item.title
        holder.description.text = item.description
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = items.size
}
