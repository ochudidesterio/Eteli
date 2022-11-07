package com.virus.eteli.data


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RandomMeal(
    @SerializedName("meals")
    @Expose
    val meals: List<Meal>
)