package com.example.test.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.RetrofitClient
import com.example.test.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.test.ui.home.ProductItem

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadProducts()

        return view
    }

    private fun loadProducts() {
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKEN", null)
        Log.d("TOKEN_CHECK", "Token: $token")


        if (token != null) {
            RetrofitClient.instance.searchProducts("Bearer $token", "").enqueue(object : Callback<List<ProductItem>> {
                override fun onResponse(call: Call<List<ProductItem>>, response: Response<List<ProductItem>>) {
                    if (response.isSuccessful && response.body() != null) {
                        val productList = response.body()!!
                        adapter = RecyclerViewAdapter(productList)
                        binding.recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
                    // можно вывести Toast или лог
                        Log.e("API_FAILURE", "Сетевая ошибка: ${t.localizedMessage}")

                }
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
