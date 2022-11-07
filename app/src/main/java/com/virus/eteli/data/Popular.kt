package com.virus.eteli.data


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Popular(
    @SerializedName("idMeal")
    @Expose
    val idMeal: String,
    @SerializedName("strMeal")
    @Expose
    val strMeal: String,
    @SerializedName("strMealThumb")
    @Expose
    val strMealThumb: String
)