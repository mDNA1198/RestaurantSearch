package com.manish.restaurantsearch.base

import androidx.lifecycle.ViewModel
import com.manish.restaurantsearch.data.models.menus.Menus
import com.manish.restaurantsearch.data.models.restaurant.Restaurants

open class BaseViewModel : ViewModel() {

    var restaurantList = ArrayList<Restaurants>()
    var menuItemsList = ArrayList<Menus>()


}