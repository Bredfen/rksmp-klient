package com.example.test.ui.like

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.ui.sbornik.Product

class LikesAdapter(private var likedProducts: List<Product>) : RecyclerView.Adapter<LikesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.likes_item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = likedProducts[position]

        val resId = holder.itemView.context.resources.getIdentifier(product.imageResId, "drawable", holder.itemView.context.packageName)
        holder.productImage.setImageResource(resId)
        holder.productName.text = product.name
        holder.productRating.text = product.rating.toString()
        holder.productPrice.text = String.format("%.2f₽", product.price)
        holder.productRate.rating = product.rating.toFloat()
    }

    override fun getItemCount(): Int = likedProducts.size

    fun updateProducts(newProducts: List<Product>) {
        likedProducts = newProducts
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.imageLike)
        val productName: TextView = itemView.findViewById(R.id.nameLikes)
        val productRating: TextView = itemView.findViewById(R.id.rating_TV)
        val productPrice: TextView = itemView.findViewById(R.id.priceLikes)
        val productRate: RatingBar = itemView.findViewById(R.id.rating)
    }
}