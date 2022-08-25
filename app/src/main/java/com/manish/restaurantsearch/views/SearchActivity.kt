package com.manish.restaurantsearch.views

import android.os.Bundle
import com.manish.restaurantsearch.adapters.CuisineSearchAdapter
import com.manish.restaurantsearch.adapters.MenuItemSearchAdapter
import com.manish.restaurantsearch.adapters.RestaurantSearchAdapter
import com.manish.restaurantsearch.base.BaseActivity
import com.manish.restaurantsearch.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val menuItemAdapter: MenuItemSearchAdapter by lazy {
        MenuItemSearchAdapter()
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
    }

    private fun initViews(){
        supportActionBar?.title = "Search"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}