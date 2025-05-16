package com.example.test.ui.sbornik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class SbornikFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: List<Product>

    private var category: String? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString("category")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_catalog, container, false)

        recyclerView = rootView.findViewById(R.id.catalogResView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadProductsFromAssets()

        return rootView
    }

    private fun loadProductsFromAssets() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val jsonString = requireContext().assets.open("products.json").bufferedReader()
                    .use { it.readText() }
                productList = Gson().fromJson(jsonString, Array<Product>::class.java).toList()
                withContext(Dispatchers.Main) {
                    setupRecyclerView()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun setupRecyclerView() {
        val filteredProducts = productList.filter { it.category == category }
        productAdapter = ProductAdapter(requireContext(), filteredProducts,
            onLikeClick = { product ->
                sharedViewModel.addProduct(product)
            },
            onBuyClick = { product ->
                sharedViewModel.addProduct(product)
            }
        )
        recyclerView.adapter = productAdapter
        productAdapter.notifyDataSetChanged()
    }
}