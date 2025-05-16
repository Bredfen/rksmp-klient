package com.example.test.ui.catalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.ui.home.ProductItem

class ProductAdapter(private val context: Context, private var items: List<ProductItem>) : BaseAdapter() {

    override fun getCount() = items.size
    override fun getItem(position: Int) = items[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.sbornik_item, parent, false)
        val item = items[position]

        val image = view.findViewById<ImageView>(R.id.imageView9)
        val title = view.findViewById<TextView>(R.id.nameTextView)
        val price = view.findViewById<TextView>(R.id.priceTextView)

        title.text = item.title
        price.text = "${item.price} ₽"
        Glide.with(context).load(item.imageLink).into(image)

        // Рейтинг можно убрать или зафиксировать
        val rating = view.findViewById<RatingBar>(R.id.rating)
        val ratingText = view.findViewById<TextView>(R.id.rating_TV)
        rating.rating = 4.0f
        ratingText.text = "4.0"

        return view
    }

    fun updateData(newItems: List<ProductItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
