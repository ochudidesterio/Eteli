package com.virus.eteli.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.eteli.data.Popular
import com.virus.eteli.databinding.PopularMealItemBinding

class PopularMealAdapter(): RecyclerView.Adapter<PopularMealAdapter.PopularMealHolder>() {
    private var popularMeals = ArrayList<Popular>()
    lateinit var onClickMeal : ((Popular)->Unit)
    class PopularMealHolder( val binding: PopularMealItemBinding):RecyclerView.ViewHolder(binding.root)

    fun setPopularMeals(list: ArrayList<Popular>){
        this.popularMeals = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealHolder {
        return PopularMealHolder(PopularMealItemBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(popularMeals[position].strMealThumb)
            .into(holder.binding.imgPopular)
        holder.itemView.setOnClickListener {
            onClickMeal.invoke(popularMeals[position])
        }
    }

    override fun getItemCount(): Int {
        return popularMeals.size
    }
}