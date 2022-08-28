package com.manish.restaurantsearch.data.models.menus

import com.google.gson.annotations.SerializedName

data class MenuData(
    @SerializedName("menus") var menus: ArrayList<Menus> = arrayListOf()
)
