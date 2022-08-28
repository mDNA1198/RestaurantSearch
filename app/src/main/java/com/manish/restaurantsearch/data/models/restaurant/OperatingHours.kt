package com.manish.restaurantsearch.data.models.restaurant

import com.google.gson.annotations.SerializedName

data class OperatingHours(
    @SerializedName("Monday") var monday: String? = null,
    @SerializedName("Tuesday") var tuesday: String? = null,
    @SerializedName("Wednesday") var wednesday: String? = null,
    @SerializedName("Thursday") var thursday: String? = null,
    @SerializedName("Friday") var friday: String? = null,
    @SerializedName("Saturday") var saturday: String? = null,
    @SerializedName("Sunday") var sunday: String? = null
)
