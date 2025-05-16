package com.example.test.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.ui.sbornik.Product

class BasketAdapter(
    private val context: Context,
    private var basketProducts: MutableList<Product>
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_basket, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val product = basketProducts[position]
        val resId = context.resources.getIdentifier(product.imageResId, "drawable", context.packageName)
        holder.productName.text = product.name
        holder.productPrice.text = String.format("%.2f₽", product.price)
        holder.productImage.setImageResource(resId)
    }

    override fun getItemCount(): Int = basketProducts.size

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.nameTextView)
        val productPrice: TextView = itemView.findViewById(R.id.priceTextView)
        val productImage: ImageView = itemView.findViewById(R.id.imageViewBasket)
    }

    fun updateProducts(newProducts: List<Product>) {
        basketProducts = newProducts.toMutableList()
        notifyDataSetChanged()
    }
}
