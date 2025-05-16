package com.example.test.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.FragmentNotificationsBinding
import com.example.test.ui.sbornik.SharedViewModel

class NotificationsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: BasketAdapter? = null

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        layoutManager = LinearLayoutManager(requireContext())
        adapter = BasketAdapter(requireContext(), sharedViewModel.basketProducts.value ?: mutableListOf())

        binding.ResViewBasket.layoutManager = layoutManager
        binding.ResViewBasket.adapter = adapter

        // Наблюдаем за изменениями в списке понравившихся продуктов
        sharedViewModel.basketProducts.observe(viewLifecycleOwner) { basketProducts ->
            // Обновляем адаптер с новым списком продуктов
            adapter?.updateProducts(basketProducts)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
