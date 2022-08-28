package com.manish.restaurantsearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.restaurantsearch.R
import com.manish.restaurantsearch.data.models.restaurant.Restaurants
import com.manish.restaurantsearch.databinding.RowForSearchByRestaurantBinding

class RestaurantSearchAdapter :
    ListAdapter<Restaurants, RestaurantSearchAdapter.RestaurantSearchViewHolder>(
        RestaurantSearchItemDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantSearchViewHolder {
        return RestaurantSearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_for_search_by_restaurant,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RestaurantSearchViewHolder(private val binding: RowForSearchByRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem: Restaurants) {
            with(binding) {
                itemData = menuItem
                Glide.with(itemView.context).load(menuItem.photograph).into(restaurantIV)
                executePendingBindings()
            }
        }
    }
}

private class RestaurantSearchItemDiffCallback : DiffUtil.ItemCallback<Restaurants>() {

    override fun areItemsTheSame(
        oldItem: Restaurants,
        newItem: Restaurants
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Restaurants,
        newItem: Restaurants
    ): Boolean {
        return oldItem == newItem
    }
}