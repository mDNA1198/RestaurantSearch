package com.manish.restaurantsearch.adapters

import android.util.Log
import com.manish.restaurantsearch.databinding.RowForSearchByMenuItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manish.restaurantsearch.R
import com.manish.restaurantsearch.data.models.search_result.MenuItemSearchResult

class MenuItemSearchAdapter :
    ListAdapter<MenuItemSearchResult, MenuItemSearchAdapter.MenuSearchViewHolder>(
        MenuItemSearchDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuSearchViewHolder {
        return MenuSearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_for_search_by_menu_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MenuSearchViewHolder(private val binding: RowForSearchByMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem: MenuItemSearchResult) {
            Log.e("bind", "bindCalled $menuItem")
            val adapter = SingleMenuItemAdapter()
            adapter.submitList(menuItem.menuItemList)
            with(binding) {
                data = menuItem
                executePendingBindings()
                menuItemsRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
                menuItemsRV.adapter = adapter
                adapter.submitList(menuItem.menuItemList)
            }
        }
    }
}

private class MenuItemSearchDiffCallback : DiffUtil.ItemCallback<MenuItemSearchResult>() {

    override fun areItemsTheSame(
        oldItem: MenuItemSearchResult,
        newItem: MenuItemSearchResult
    ): Boolean {
        return oldItem.restaurantId == newItem.restaurantId
    }

    override fun areContentsTheSame(
        oldItem: MenuItemSearchResult,
        newItem: MenuItemSearchResult
    ): Boolean {
        return oldItem == newItem
    }
}
