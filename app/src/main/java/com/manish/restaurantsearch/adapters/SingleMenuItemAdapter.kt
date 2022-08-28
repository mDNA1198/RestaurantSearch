package com.manish.restaurantsearch.adapters

import com.manish.restaurantsearch.data.models.menus.MenuItems
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.restaurantsearch.R
import com.manish.restaurantsearch.databinding.RowForMenuItemsBinding

class SingleMenuItemAdapter :
    ListAdapter<MenuItems, SingleMenuItemAdapter.SingleMenuItemViewHolder>(
        SingleMenuItemDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMenuItemViewHolder {
        return SingleMenuItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_for_menu_items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SingleMenuItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SingleMenuItemViewHolder(private val binding: RowForMenuItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem: MenuItems) {
            with(binding) {
                itemData = menuItem
                Glide.with(itemView.context).load(menuItem.images).into(menuItemIV)
                executePendingBindings()
            }
        }
    }
}

private class SingleMenuItemDiffCallback : DiffUtil.ItemCallback<MenuItems>() {

    override fun areItemsTheSame(
        oldItem: MenuItems,
        newItem: MenuItems
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MenuItems,
        newItem: MenuItems
    ): Boolean {
        return oldItem == newItem
    }
}
