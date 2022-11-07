package com.virus.eteli.data


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Category(
    @SerializedName("idCategory")
    @Expose
    val idCategory: String,
    @SerializedName("strCategory")
    @Expose
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    @Expose
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    @Expose
    val strCategoryThumb: String
)