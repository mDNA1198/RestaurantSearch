package com.manish.restaurantsearch.viewmodels

import androidx.lifecycle.MutableLiveData
import com.manish.restaurantsearch.base.BaseViewModel
import com.manish.restaurantsearch.data.models.menus.MenuItems
import com.manish.restaurantsearch.data.models.restaurant.Restaurants
import com.manish.restaurantsearch.data.models.search_result.MenuItemSearchResult

class SearchViewModel : BaseViewModel() {

    var searchedResult = ArrayList<MenuItemSearchResult>()

    var menuItemsSearchResult = MutableLiveData<List<MenuItems>>()
    var restaurantSearchResult = MutableLiveData<List<Restaurants>>()
    var cuisineSearchResult = MutableLiveData<List<Restaurants>>()
    var menuItemsUpdated = MutableLiveData<Boolean>().also { it.value = false }

    fun searchMenuItems(input: String){
        /*menuItemsList.forEach { item ->
            item.categories.forEach{ item2 ->
                item2.menuItems.forEach { item3 ->
                    if(item3.name.contains(input, ignoreCase = true)){
                        item3.apply {
                            restaurantId = item.restaurantId.toString()
                            restaurantName = getRestaurantName(item.restaurantId)
                        }
                        addToList(item3)
                    }
                }
            }
        }*/
        val searchResult: List<MenuItems> = menuItemsList.flatMap{ rest -> rest.categories.asSequence() }.flatMap{ it.menuItems.asSequence() }.filter { it.name.contains(input, ignoreCase = true) }
        menuItemsSearchResult.postValue(searchResult)
    }

    /*private fun getRestaurantName(restaurantId: Int): String{
       return restaurantList.filter { it.id == restaurantId }.map { it.name }[0] ?: ""
    }*/

    /*private fun addToList(item: MenuItems){
        val searchItem: MenuItemSearchResult = MenuItemSearchResult(item.restaurantName, item.restaurantId.toInt()).also {
            it.addInList(item)
        }
        when{
            searchedResult.any { it.restaurantId == item.restaurantId.toInt() } -> {
                searchedResult.filter { it.restaurantId == item.restaurantId.toInt() }[0].menuItemList.add(item)
            }
            searchedResult.isEmpty() -> {
                searchedResult.add(searchItem)
            }
            else -> {
                searchedResult.add(searchItem)
            }
        }
        menuItemsUpdated.postValue(true)
    }*/

    fun searchCuisine(input: String) {
        val searchResult = restaurantList.filter { it.cuisineType.contains(input, ignoreCase = true) }
        cuisineSearchResult.postValue(searchResult)
    }

    fun searchRestaurant(input: String) {
        val searchResult = restaurantList.filter { it.name.contains(input, ignoreCase = true)}
        restaurantSearchResult.postValue(searchResult)
    }
}