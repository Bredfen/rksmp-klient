package com.example.test.ui.order

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.test.R



class EdinicaTovaraAdapter(private val context: Context, private val dataList: List<EdinicaTovara>): BaseAdapter() {
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_zakaz_edinica_tovara, parent, false)

        val item: EdinicaTovara = dataList[position]

        val itemImage: ImageView = view.findViewById(R.id.image_tovar)
        itemImage.setImageResource(item.imageTovar)

        val itemName: TextView = view.findViewById(R.id.nameTV)
        itemName.text = item.nameTovar

        val itemKod: TextView = view.findViewById(R.id.kod_TV)
        itemKod.text = item.kodTovar

        val itemColvo: TextView = view.findViewById(R.id.colv_TV)
        itemColvo.text = item.kolvoTovar

        val itemPrice: TextView = view.findViewById(R.id.price_TV)
        itemPrice.text = item.priceTovar

        return view
    }

}