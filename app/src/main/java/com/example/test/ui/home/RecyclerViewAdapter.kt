package com.example.test.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R

class RecyclerViewAdapter(private val products: List<ProductItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val textName: TextView = itemView.findViewById(R.id.name_text_view)
        val textSubtitle: TextView = itemView.findViewById(R.id.subtitle_text_view)
        val textPrice: TextView = itemView.findViewById(R.id.price_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_model, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.textName.text = product.title
        holder.textSubtitle.text = product.subtitle
        holder.textPrice.text = "${product.price} ₽"
        Glide.with(holder.itemView.context).load(product.imageLink).into(holder.image)
    }
}
