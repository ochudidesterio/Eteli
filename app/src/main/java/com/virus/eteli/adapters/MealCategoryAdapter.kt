package com.virus.eteli.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.eteli.data.Category
import com.virus.eteli.databinding.CategoryItemBinding

class MealCategoryAdapter():RecyclerView.Adapter<MealCategoryAdapter.CategoryHolder>() {
   private var categories = ArrayList<Category>()
    fun setCategories(list: ArrayList<Category>){
        this.categories = list
    }
    lateinit var onClickCategory : ((Category)->Unit)
    class CategoryHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.binding.title.text = categories[position].strCategory
        Glide.with(holder.itemView)
            .load(categories[position].strCategoryThumb)
            .into(holder.binding.categoryImage)
        holder.itemView.setOnClickListener{
            onClickCategory.invoke(categories[position])
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}