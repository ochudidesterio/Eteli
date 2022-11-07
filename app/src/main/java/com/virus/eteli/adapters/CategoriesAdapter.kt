package com.virus.eteli.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.eteli.data.Popular
import com.virus.eteli.databinding.MealBinding

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.MealHolder>() {
   private var meals = ArrayList<Popular>()
    lateinit var onClickMeal:((Popular)->Unit)
    fun setMeals(list:ArrayList<Popular>){
        meals = list
    }
    class MealHolder(val binding: MealBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        return MealHolder(MealBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        holder.binding.name.text = meals[position].strMeal
        Glide.with(holder.itemView)
            .load(meals[position].strMealThumb)
            .into(holder.binding.imgMeal)
        holder.itemView.setOnClickListener{
            onClickMeal.invoke(meals[position])
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}