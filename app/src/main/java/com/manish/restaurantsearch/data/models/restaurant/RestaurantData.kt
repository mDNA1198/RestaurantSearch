package com.manish.restaurantsearch.data.models.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantData(
    @SerializedName("restaurants") var restaurants : ArrayList<Restaurants> = arrayListOf()
)
