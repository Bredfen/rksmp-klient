package com.example.test.ui.order

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.test.R

class ZakazAdapter(private val context: Context, private val dataList: List<ItemZakaz>) : BaseAdapter()  {
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
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_fragment_zakaz_listview, parent, false)

        val item: ItemZakaz = dataList[position]

        // Настройка макета с CardView в качестве элемента списка
        val cardView: CardView = view.findViewById(R.id.card_view)
        val numberZakazTextView: TextView = view.findViewById(R.id.number_zakaz)
        val dateZakazTextView: TextView = view.findViewById(R.id.dateZakaz)
        val adressTextView: TextView = view.findViewById(R.id.text_adress)
        val numberPhoneTextView: TextView = view.findViewById(R.id.text_number_phone)

        numberZakazTextView.text = item.numberZakaz.toString()
        dateZakazTextView.text = item.dateZakaz
        adressTextView.text = item.adress
        numberPhoneTextView.text = item.numberPhone

        // Настройка вложенного ListView для списка товаров заказа
        val edinicaTovaraListView: ListView = view.findViewById(R.id.item_zakaz_list_view)
        val edinicaTovaraAdapter = EdinicaTovaraAdapter(context, item.EdinicaTovara)
        edinicaTovaraListView.adapter = edinicaTovaraAdapter

        return view
    }
}