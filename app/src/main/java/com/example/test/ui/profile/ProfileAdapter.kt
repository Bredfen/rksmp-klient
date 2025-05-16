package com.example.test.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.test.R


class ProfileAdapter(private val context: Context, private val dataList: List<ItemProfile>): BaseAdapter() {
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
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_profile, parent, false)

        val item: ItemProfile = dataList[position]

        val itemImage: ImageView = view.findViewById(R.id.image_item_profile)
        itemImage.setImageResource(item.imageprofile)

        val itemName: TextView = view.findViewById(R.id.name_profile)
        itemName.text = item.nameprofile

        return view
    }
}