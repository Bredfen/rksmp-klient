package com.example.test.ui.sbornik

// ProductAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val onLikeClick: (Product) -> Unit,
    private val onBuyClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sbornik_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        val resId = context.resources.getIdentifier(product.imageResId, "drawable", context.packageName)
        holder.productImage.setImageResource(resId)
        holder.productName.text = product.name
        holder.productRating.text = product.rating.toString()
        holder.productPrice.text = String.format("%.2f₽", product.price)
        holder.productRate.rating = product.rating.toFloat()

        holder.likeButton.setOnClickListener {
            onLikeClick(product)
        }

        holder.buyButton.setOnClickListener {
            onBuyClick(product)
        }
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.imageView9)
        val productName: TextView = itemView.findViewById(R.id.nameTextView)
        val productRating: TextView = itemView.findViewById(R.id.rating_TV)
        val productPrice: TextView = itemView.findViewById(R.id.priceTextView)
        val productRate: RatingBar = itemView.findViewById(R.id.rating)
        val likeButton: ImageButton = itemView.findViewById(R.id.likeButton)
        val buyButton: Button = itemView.findViewById(R.id.buyButton)
    }
}

