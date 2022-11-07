package com.virus.eteli.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.virus.eteli.R
import com.virus.eteli.adapters.MealCategoryAdapter
import com.virus.eteli.adapters.PopularMealAdapter
import com.virus.eteli.data.Category
import com.virus.eteli.data.Meal
import com.virus.eteli.data.Popular
import com.virus.eteli.databinding.FragmentHomeBinding
import com.virus.eteli.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var popularMealAdapter: PopularMealAdapter
    private lateinit var mealCategoryAdapter: MealCategoryAdapter
    private lateinit var meal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity())[HomeViewModel::class.java]
        popularMealAdapter = PopularMealAdapter()
        mealCategoryAdapter = MealCategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.apply {
            title = resources.getString(R.string.app_name)
        }
        binding.toolBar.inflateMenu(R.menu.cart_menu)
        viewModel.getRandomMeal()
        viewModel.getPopularMeals("Seafood")
        viewModel.getMealCategories()
        showRandomMeal()
        showPopularMeals()
        showCategories()
        onPopularItemClicked()
        onCategoryItemClicked()
        binding.imgRandom.setOnClickListener{
            onRandomMealClicked()
        }

    }

    private fun onRandomMealClicked() {
        viewModel.mealId.postValue(meal.idMeal)
        findNavController().navigate(R.id.action_homeFragment_to_mealDetailFragment)
    }

    private fun onCategoryItemClicked() {
        mealCategoryAdapter.onClickCategory ={
        viewModel.category.value = it
            findNavController().navigate(R.id.action_homeFragment_to_categoriesFragment)
        }
    }

    private fun onPopularItemClicked() {
        popularMealAdapter.onClickMeal ={
            viewModel.mealId.value = it.idMeal
          findNavController().navigate(R.id.action_homeFragment_to_mealDetailFragment)
        }
    }

    private fun showCategories() {
        viewModel.mealCategoriesObserver().observe(viewLifecycleOwner){
            mealCategoryAdapter.setCategories(it as ArrayList<Category>)
            binding.rvMealCategories.apply {
                layoutManager = GridLayoutManager(activity,3,GridLayoutManager.VERTICAL,false)
                adapter = mealCategoryAdapter
            }
        }
    }

    private fun showPopularMeals() {
        viewModel.popularMealObserver().observe(viewLifecycleOwner){
            popularMealAdapter.setPopularMeals(it as ArrayList<Popular>)
            binding.rvPopular.apply {
                layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                adapter = popularMealAdapter
            }
        }
    }

    private fun showRandomMeal() {
        viewModel.randomMealObserver().observe(viewLifecycleOwner) {
            meal = it
            Glide.with(this)
                .load(it.strMealThumb)
                .into(binding.imgRandom)

        }
    }

}