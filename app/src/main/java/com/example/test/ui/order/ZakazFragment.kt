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
            EdinicaTovara("Тонометр Omron M2 Classic","00000001","1","2499.00₽",R.drawable.tonometr_omron_m2),
            EdinicaTovara("Глюкометр OneTouch Select Plusc","00000002","1","1599.00₽",R.drawable.glukometr_onetouch)
        )

        val EdinicaTovara2 = listOf(
            EdinicaTovara("Омепразол 20мг №30","00000003","1","129.00₽",R.drawable.omeprazol_20mg),
            EdinicaTovara("Нурофен 400мг №12","00000004","1","189.00₽",R.drawable.nurofen_400mg),
        )

        val EdinicaTovara3 = listOf(
            EdinicaTovara("Аспирин Кардио 100мг №30","00000005","1","239.00₽",R.drawable.aspirin_cardio_100mg),
            EdinicaTovara("ОмегаВит D3 2000МЕ №60","00000006","1","399.00",R.drawable.omegavit_d3),
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