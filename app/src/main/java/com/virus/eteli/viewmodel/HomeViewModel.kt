package com.virus.eteli.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virus.eteli.data.*
import com.virus.eteli.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularMealsLivedate = MutableLiveData<List<Popular>>()
    private var mealCategoriesLiveData = MutableLiveData<List<Category>>()
    var mealId = MutableLiveData<String>()
    var meal = MutableLiveData<Meal>()
    var category = MutableLiveData<Category>()



    fun getMeal():LiveData<Meal>{
        return meal
    }
    fun getMealById(id: String){
        RetrofitInstance.api.getAmeal(id).enqueue(object : Callback<RandomMeal>{
            override fun onResponse(call: Call<RandomMeal>, response: Response<RandomMeal>) {
                if(response.body() != null){
                    meal.value = response.body()!!.meals[0]
                }


            }

            override fun onFailure(call: Call<RandomMeal>, t: Throwable) {
                Log.e( "onFailure: ",t.message.toString() )
            }

        })
    }
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<RandomMeal> {
            override fun onResponse(call: Call<RandomMeal>, response: Response<RandomMeal>) {
                if(response.body() != null){
                    randomMealLiveData.value = response.body()!!.meals[0]
                }
            }

            override fun onFailure(call: Call<RandomMeal>, t: Throwable) {
                Log.e( "onFailure: ", t.message.toString())
            }

        })
    }
    fun getPopularMeals(name : String){
        RetrofitInstance.api.getPopularMeal(name).enqueue(object : Callback<PopularMeals>{
            override fun onResponse(call: Call<PopularMeals>, response: Response<PopularMeals>) {
                if(response.body() != null){
                    popularMealsLivedate.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<PopularMeals>, t: Throwable) {
                Log.e( "onFailure: ",t.message.toString() )
            }

        })
    }
    fun getMealCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<MealCategories>{
            override fun onResponse(
                call: Call<MealCategories>,
                response: Response<MealCategories>
            ) {
                if(response.body() != null){
                    mealCategoriesLiveData.value = response.body()!!.categories
                }
            }

            override fun onFailure(call: Call<MealCategories>, t: Throwable) {
                Log.e("onFailure: ",t.message.toString() )
            }

        })
    }
    fun randomMealObserver():LiveData<Meal>{
        return randomMealLiveData
    }
    fun popularMealObserver():LiveData<List<Popular>>{
        return popularMealsLivedate
    }
    fun mealCategoriesObserver():LiveData<List<Category>>{
        return mealCategoriesLiveData
    }
}