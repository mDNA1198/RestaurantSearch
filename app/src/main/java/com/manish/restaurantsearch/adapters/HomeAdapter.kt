package com.manish.restaurantsearch.adapters

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

class HomeAdapter :
    ListAdapter<MenuItemSearchResult, HomeAdapter.HomeViewHolder>(
        HomeAdapterDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_for_search_by_menu_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeViewHolder(private val binding: RowForSearchByMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            /*binding.setClickListener { view ->
                binding.viewModel?.plantId?.let { plantId ->
                    navigateToPlant(plantId, view)
                }
            }*/
        }

        /*private fun navigateToPlant(plantId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionViewPagerFragmentToPlantDetailFragment(plantId)
            view.findNavController().navigate(direction)
        }*/

        fun bind(plantings: MenuItemSearchResult) {
            val adapter = SingleMenuItemAdapter()
            adapter.submitList(plantings.menuItemList)
            with(binding) {
                data = plantings
                executePendingBindings()
                menuItemsRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
                menuItemsRV.adapter = adapter
            }
        }
    }
}

private class HomeAdapterDiffCallback : DiffUtil.ItemCallback<MenuItemSearchResult>() {

    override fun areItemsTheSame(
        oldItem: MenuItemSearchResult,
        newItem: MenuItemSearchResult
    ): Boolean {
        return oldItem.restaurantName == newItem.restaurantName
    }

    override fun areContentsTheSame(
        oldItem: MenuItemSearchResult,
        newItem: MenuItemSearchResult
    ): Boolean {
        return oldItem == newItem
    }
}
