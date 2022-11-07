package com.virus.eteli.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.virus.eteli.databinding.FragmentMealDetailBinding
import com.virus.eteli.viewmodel.HomeViewModel


class MealDetailFragment : Fragment() {
private lateinit var binding: FragmentMealDetailBinding
private lateinit var viewModel : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity())[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMealById(viewModel.mealId.value!!)


        viewModel.getMeal().observe(viewLifecycleOwner){
            Glide.with(requireActivity())
                .load(it.strMealThumb)
                .into(binding.mealImage)
            binding.collapsingToolBar.apply {
                title = it.strMeal
            }
            binding.txtCategory.text = "Category: ${it.strCategory}"
            binding.location.text ="Location: ${it.strArea}"
            binding.textInstructions.text = it.strInstructions
        }
    }

}