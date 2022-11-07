package com.virus.eteli.data


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MealCategories(
    @SerializedName("categories")
    @Expose
    val categories: List<Category>
)