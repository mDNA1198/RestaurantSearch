package com.manish.restaurantsearch.data.models.menus

import com.google.gson.annotations.SerializedName

data class Menus(
    @SerializedName("restaurantId") var restaurantId: Int = 0,
    @SerializedName("categories") var categories: ArrayList<Categories> = arrayListOf()
)
