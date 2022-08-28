package com.manish.restaurantsearch.data.models.menus

import com.google.gson.annotations.SerializedName

data class MenuItems(
    var restaurantId: String = "0",
    var restaurantName: String = "",
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") var description: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("images") var images: String = ""
)
