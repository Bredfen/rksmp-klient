package com.example.test.ui.catalog

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.RetrofitClient
import com.example.test.databinding.FragmentDashboardBinding
import com.example.test.ui.home.ProductItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var listView: ListView
    private lateinit var progressBar: ProgressBar
    private lateinit var editTextSearchQuery: EditText
    private lateinit var clearButton: Button
    private lateinit var placeholderContainer: LinearLayout
    private lateinit var placeholderText: TextView
    private lateinit var retryButton: Button

    private lateinit var searchHistoryListView: ListView
    private lateinit var clearHistoryButton: Button
    private lateinit var historyAdapter: ArrayAdapter<String>
    private val searchHistory = mutableListOf<String>()
    private val sharedPrefsKey = "SearchHistory"

    private lateinit var adapter: ProductAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var searchRunnable: Runnable? = null
    private var lastQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root = binding.root

        listView = binding.listView
        progressBar = root.findViewById(R.id.progressBar)
        editTextSearchQuery = root.findViewById(R.id.editTextText)
        clearButton = root.findViewById(R.id.clearButton)
        placeholderContainer = root.findViewById(R.id.placeholderContainer)
        placeholderText = root.findViewById(R.id.placeholderText)
        retryButton = root.findViewById(R.id.retryButton)

        searchHistoryListView = root.findViewById(R.id.searchHistoryListView)
        clearHistoryButton = root.findViewById(R.id.clearHistoryButton)

        adapter = ProductAdapter(requireContext(), emptyList())
        listView.adapter = adapter

        historyAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, searchHistory)
        searchHistoryListView.adapter = historyAdapter
        loadSearchHistory()

        editTextSearchQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
                searchRunnable?.let { handler.removeCallbacks(it) }
                searchRunnable = Runnable { performSearch(s.toString()) }
                handler.postDelayed(searchRunnable!!, 700)
            }
        })

        editTextSearchQuery.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && searchHistory.isNotEmpty()) {
                searchHistoryListView.visibility = View.VISIBLE
            } else {
                searchHistoryListView.visibility = View.GONE
            }
        }

        searchHistoryListView.setOnItemClickListener { _, _, position, _ ->
            val selectedQuery = searchHistory[position]
            editTextSearchQuery.setText(selectedQuery)
            performSearch(selectedQuery)
            searchHistoryListView.visibility = View.GONE
        }

        clearButton.setOnClickListener {
            editTextSearchQuery.text.clear()
            hideKeyboard()
        }

        clearHistoryButton.setOnClickListener {
            searchHistory.clear()
            saveSearchHistory()
            historyAdapter.notifyDataSetChanged()
            searchHistoryListView.visibility = View.GONE
        }

        retryButton.setOnClickListener {
            performSearch(lastQuery)
        }

        return root
    }

    private fun performSearch(query: String) {
        val prefs = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = prefs.getString("TOKEN", null) ?: return

        lastQuery = query
        showProgressBar(true)
        showPlaceholder(false)

        RetrofitClient.instance.searchProducts("Bearer $token", query).enqueue(object : Callback<List<ProductItem>> {
            override fun onResponse(call: Call<List<ProductItem>>, response: Response<List<ProductItem>>) {
                showProgressBar(false)
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    if (result.isEmpty()) {
                        showPlaceholder(true, "Ничего не найдено", false)
                        adapter.updateData(emptyList())
                    } else {
                        showPlaceholder(false)
                        adapter.updateData(result)
                        if (!searchHistory.contains(query)) {
                            searchHistory.add(0, query)
                            if (searchHistory.size > 10) searchHistory.removeLast()
                            saveSearchHistory()
                            historyAdapter.notifyDataSetChanged()
                        }
                    }
                } else {
                    showPlaceholder(true, "Ошибка загрузки", true)
                }
            }

            override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
                showProgressBar(false)
                showPlaceholder(true, "Ошибка сети: ${t.localizedMessage}", true)
            }
        })
    }

    private fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showPlaceholder(show: Boolean, message: String = "", showRetry: Boolean = false) {
        placeholderContainer.visibility = if (show) View.VISIBLE else View.GONE
        placeholderText.text = message
        retryButton.visibility = if (showRetry) View.VISIBLE else View.GONE
    }

    private fun saveSearchHistory() {
        val prefs = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        prefs.edit().putStringSet(sharedPrefsKey, searchHistory.toSet()).apply()
        clearHistoryButton.visibility = if (searchHistory.isNotEmpty()) View.VISIBLE else View.GONE
    }


    private fun loadSearchHistory() {
        val prefs = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val saved = prefs.getStringSet(sharedPrefsKey, emptySet())?.toList()?.reversed() ?: emptyList()
        searchHistory.clear()
        searchHistory.addAll(saved.take(10))
        historyAdapter.notifyDataSetChanged()

        // Показывать кнопку, только если история не пуста
        clearHistoryButton.visibility = if (searchHistory.isNotEmpty()) View.VISIBLE else View.GONE
    }


    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        handler.removeCallbacksAndMessages(null)
    }
}
