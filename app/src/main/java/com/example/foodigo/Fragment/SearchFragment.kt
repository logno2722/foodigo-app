package com.example.foodigo.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodigo.R
import com.example.foodigo.adapter.MenuAdapter
import com.example.foodigo.databinding.FragmentSearchBinding
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val originalMenuFoodName = listOf("Burger", "Momo", "Momo", "Momo", "Momo", "Momo","Momo", "Sandwich", "Momo", "Item", "Sandwich", "Momo")
    private val originalMenuItemPrice = listOf("$5", "$6", "$8", "$9", "$10", "$10","$5", "$6", "$8", "$9", "$10", "$10")
    private val originalMenuImage = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu2,
        R.drawable.menu3
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter= MenuAdapter(filteredMenuFoodName,filteredMenuItemPrice,filteredMenuImage, requireContext()
        )
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter=adapter

        setupSearchView()

        showAllMenu()

        return binding.root
    }

    private fun showAllMenu() {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuItemPrice.addAll(originalMenuItemPrice)
        filteredMenuImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // Fix 1: The parameter is named 'newText' (or 'p0' by default).
            // We use 'newText' which is more descriptive.
            override fun onQueryTextChange(newText: String?): Boolean {
                // Use the safe call operator to handle nullability
                filterMenuItems(newText.orEmpty())
                return true // Indicate that you have handled the action
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            private fun filterMenuItems(query: String) {
                // Before filtering, check if the query is empty/blank
                if (query.isBlank()) {
                    showAllMenu()
                    return
                }

                filteredMenuFoodName.clear()
                filteredMenuItemPrice.clear()
                filteredMenuImage.clear()

                originalMenuFoodName.forEachIndexed { index, foodName ->
                    // Fix 2: 'query' is already a String, remove redundant .toString()
                    if (foodName.contains(query, ignoreCase = true)) {
                        filteredMenuFoodName.add(foodName)
                        filteredMenuItemPrice.add(originalMenuItemPrice[index])
                        filteredMenuImage.add(originalMenuImage[index])
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }
}