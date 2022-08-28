package com.manish.restaurantsearch.data.models.search_result

import com.manish.restaurantsearch.data.models.menus.MenuItems

data class MenuItemSearchResult(
    var restaurantName: String,
    var restaurantId: Int,
    var menuItemList: ArrayList<MenuItems> = arrayListOf()
){
    fun addInList(item: MenuItems){
        menuItemList.add(item)
    }
}