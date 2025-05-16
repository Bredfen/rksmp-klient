package com.example.test.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentZakazBinding


class ZakazFragment : Fragment() {

    private var _binding: FragmentZakazBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentZakazBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var listView = binding.itemFragmentZakazListview

        val EdinicaTovara1 = listOf(
            EdinicaTovara("Xiaomi Redmi Note 6","00000001","1","6990.00₽",R.drawable.smart_1),
            EdinicaTovara("Xiaomi Redmi Note 7","00000002","1","8990.00₽",R.drawable.smart_2)
        )

        val EdinicaTovara2 = listOf(
            EdinicaTovara("Xiaomi Redmi Note 8","00000003","1","10990.00₽",R.drawable.smart_3),
            EdinicaTovara("Xiaomi Redmi Note 9","00000004","1","12990.00₽",R.drawable.smart_4),
        )

        val EdinicaTovara3 = listOf(
            EdinicaTovara("Xiaomi Redmi Note 10","00000005","1","14990.00₽",R.drawable.smart_5),
            EdinicaTovara("Xiaomi Redmi Note 11","00000006","1","16990.00₽",R.drawable.smart_6),
        )

        val dataList = listOf (
            ItemZakaz("P-123456789", "21.04.2024","пр-т Вернадского, 78","+79041540587",EdinicaTovara1),
            ItemZakaz("P-123456790", "23.04.2024","ул. Ленина, 26","+79041540587", EdinicaTovara2),
            ItemZakaz("P-123456791", "25.04.2024","ул. Декабристов, 45","+79041540587", EdinicaTovara3),

        )

        val adapter = ZakazAdapter(requireContext(), dataList)
        listView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}