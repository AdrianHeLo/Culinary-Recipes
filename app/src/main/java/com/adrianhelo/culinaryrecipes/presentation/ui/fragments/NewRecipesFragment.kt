package com.adrianhelo.culinaryrecipes.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.adrianhelo.culinaryrecipes.R
import com.adrianhelo.culinaryrecipes.databinding.FragmentNewRecipesBinding
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel
import com.adrianhelo.culinaryrecipes.presentation.adapters.RecipeAdapter
import com.adrianhelo.culinaryrecipes.presentation.ui.MainActivity
import com.adrianhelo.culinaryrecipes.presentation.viewmodel.RecipeViewModel

class NewRecipesFragment : Fragment(R.layout.fragment_new_recipes) {

    private var _binding: FragmentNewRecipesBinding ?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        mView = view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_new_recipe, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_button_update_fragment -> {
                saveRecipe(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveRecipe(view: View){
        val recipeTittle = binding.tittleNewRecipeFragment.text.toString().trim()
        val recipeBody = binding.descriptionNewRecipeFragment.text.toString().trim()
        if (recipeTittle.isNotEmpty() && recipeBody.isNotEmpty()){
            val recipe = RecipeModel(0, recipeTittle, recipeBody)
            viewModel.insertRecipe(recipe)
            Toast.makeText(context, "Recipe has been saved successfully!!", Toast.LENGTH_LONG).show()
            view.findNavController().navigate(R.id.action_newRecipesFragment_to_homeFragment)
        }else{
            Toast.makeText(context, "PLease enter Name Recipe´s", Toast.LENGTH_LONG).show()
        }
    }

}