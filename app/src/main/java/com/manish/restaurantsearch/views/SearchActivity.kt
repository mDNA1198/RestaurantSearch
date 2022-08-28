package com.manish.restaurantsearch.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manish.restaurantsearch.adapters.CuisineSearchAdapter
import com.manish.restaurantsearch.adapters.MenuItemSearchAdapter
import com.manish.restaurantsearch.adapters.RestaurantSearchAdapter
import com.manish.restaurantsearch.adapters.SingleMenuItemAdapter
import com.manish.restaurantsearch.base.BaseActivity
import com.manish.restaurantsearch.data.models.menus.MenuData
import com.manish.restaurantsearch.data.models.restaurant.RestaurantData
import com.manish.restaurantsearch.databinding.ActivitySearchBinding
import com.manish.restaurantsearch.viewmodels.SearchViewModel
import java.io.IOException

class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    private val menuItemAdapter: MenuItemSearchAdapter by lazy {
        MenuItemSearchAdapter()
    }

    private val singleMenuItemAdapter: SingleMenuItemAdapter by lazy {
        SingleMenuItemAdapter()
    }

    private val cuisineSearchAdapter: CuisineSearchAdapter by lazy {
        CuisineSearchAdapter()
    }

    private val restaurantSearchAdapter: RestaurantSearchAdapter by lazy {
        RestaurantSearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        getData()
        viewEvents()
        initObservers()
    }

    private fun initViews(){
        supportActionBar?.title = "Search"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.menuItemsSearchResultRV.adapter = singleMenuItemAdapter
        binding.cuisinesSearchResultRV.adapter = cuisineSearchAdapter
        binding.restaurantSearchResultRV.adapter = restaurantSearchAdapter
    }

    private fun viewEvents(){
        binding.searchET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.searchMenuItems(s.toString())
                searchViewModel.searchCuisine(s.toString())
                searchViewModel.searchRestaurant(s.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })
    }

    private fun getData(){
        lateinit var jsonRestaurantDataString: String
        lateinit var jsonMenuItemDataString: String
        try {
            jsonRestaurantDataString = applicationContext.assets.open("restaurant.json").bufferedReader().use { it.readText() }
            val dataRestaurantType = object : TypeToken<RestaurantData>() {}.type
            val finalRestaurantData: RestaurantData = Gson().fromJson(jsonRestaurantDataString, dataRestaurantType)
            searchViewModel.restaurantList = finalRestaurantData.restaurants
            jsonMenuItemDataString = applicationContext.assets.open("menuitems.json").bufferedReader().use { it.readText() }
            val dataMenuItemType = object : TypeToken<MenuData>() {}.type
            val finalMenuItemData: MenuData = Gson().fromJson(jsonMenuItemDataString, dataMenuItemType)
            searchViewModel.menuItemsList = finalMenuItemData.menus
        } catch (ioException: IOException) {
            Log.e("getDataException", "$ioException")
        }
    }

    private fun initObservers(){
        /*searchViewModel.menuItemsUpdated.observe(this@SearchActivity) {
            Log.e("observer called", "data ${searchViewModel.searchedResult}")
            //menuItemAdapter.submitList(null);
            menuItemAdapter.submitList(searchViewModel.searchedResult)
        }*/

        searchViewModel.menuItemsSearchResult.observe(this@SearchActivity) {
            singleMenuItemAdapter.submitList(it)
        }

        searchViewModel.cuisineSearchResult.observe(this@SearchActivity) {
            cuisineSearchAdapter.submitList(it)
        }

        searchViewModel.restaurantSearchResult.observe(this@SearchActivity) {
            restaurantSearchAdapter.submitList(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}