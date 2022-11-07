package com.virus.eteli.api

import com.virus.eteli.data.MealCategories
import com.virus.eteli.data.PopularMeals
import com.virus.eteli.data.RandomMeal
import com.virus.eteli.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(Constants.RANDOM)
    fun getRandomMeal(): Call<RandomMeal>
    @GET(Constants.POPULAR)
    fun  getPopularMeal(@Query(value = "c")name: String):Call<PopularMeals>
    @GET(Constants.CATEGORY)
    fun getCategories():Call<MealCategories>
    @GET(Constants.MEALBYID)
    fun getAmeal(@Query(value = "i")id: String):Call<RandomMeal>
}