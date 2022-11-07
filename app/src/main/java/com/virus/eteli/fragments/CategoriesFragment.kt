package com.virus.eteli.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.virus.eteli.R
import com.virus.eteli.adapters.CategoriesAdapter
import com.virus.eteli.data.Popular
import com.virus.eteli.databinding.FragmentCategoriesBinding
import com.virus.eteli.viewmodel.HomeViewModel


class CategoriesFragment : Fragment() {
  private lateinit var binding: FragmentCategoriesBinding
  private lateinit var viewModel: HomeViewModel
  private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity())[HomeViewModel::class.java]
        categoriesAdapter = CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularMeals(viewModel.category.value!!.strCategory)
        Glide.with(requireActivity())
            .load(viewModel.category.value!!.strCategoryThumb)
            .into(binding.mealImage)
        binding.collapsingToolBar.apply {
            title = viewModel.category.value!!.strCategory
        }
        populateMeals()
        onMealItemCicked()
    }

    private fun onMealItemCicked() {
        categoriesAdapter.onClickMeal = {
            viewModel.mealId.value = it.idMeal
            findNavController().navigate(R.id.action_categoriesFragment_to_mealDetailFragment)
        }
    }

    private fun populateMeals() {
        viewModel.popularMealObserver().observe(viewLifecycleOwner){
            categoriesAdapter.setMeals(it as ArrayList<Popular>)
            Log.e( "size: ",it.size.toString() )
            binding.rvMeals.apply {
                layoutManager = GridLayoutManager(requireActivity(),2,GridLayoutManager.VERTICAL,false)
                adapter = categoriesAdapter
            }

        }
    }
}