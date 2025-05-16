package com.example.test.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.FragmentLikesBinding
import com.example.test.ui.sbornik.SharedViewModel


class LikesFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: LikesAdapter? = null

    private var _binding: FragmentLikesBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        layoutManager = LinearLayoutManager(requireContext())
        adapter = LikesAdapter(emptyList())
        binding.ResViewLikes.layoutManager = layoutManager
        binding.ResViewLikes.adapter = adapter

        sharedViewModel.likedProducts.observe(viewLifecycleOwner) { likedProducts ->
            adapter?.updateProducts(likedProducts)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}