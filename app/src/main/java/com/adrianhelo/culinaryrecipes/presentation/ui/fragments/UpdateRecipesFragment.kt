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
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.adrianhelo.culinaryrecipes.R
import com.adrianhelo.culinaryrecipes.databinding.FragmentUpdateRecipesBinding
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel
import com.adrianhelo.culinaryrecipes.presentation.ui.MainActivity
import com.adrianhelo.culinaryrecipes.presentation.viewmodel.RecipeViewModel

class UpdateRecipesFragment : Fragment(R.layout.fragment_update_recipes) {

    private var _binding: FragmentUpdateRecipesBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecipeViewModel

    private lateinit var currentRecipe: RecipeModel
    private lateinit var args: UpdateRecipesFragmentArgs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        currentRecipe = args.recipe!!
        binding.tittleUpdateFragment.setText(currentRecipe.recipeTittle)
        binding.descriptionUpdateFragment.setText(currentRecipe.recipeBody)
        binding.saveButtonUpdateFragment.setOnClickListener{
            val recipeTittle = binding.tittleUpdateFragment.text.toString().trim()
            val recipeBody = binding.descriptionUpdateFragment.text.toString().trim()
            if (recipeTittle.isNotEmpty() && recipeBody.isNotEmpty()){
                val recipe = RecipeModel(currentRecipe.id, currentRecipe.recipeTittle, currentRecipe.recipeBody)
                viewModel.updateRecipe(recipe)
                it.findNavController().navigate(R.id.action_updateRecipesFragment_to_homeFragment)
            }else{
                Toast.makeText(context, "PLease enter Name Recipe´s", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_recipe, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete -> {
                deleteRecipe()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun deleteRecipe(){
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete Recipe")
            setMessage("Are you sure you want to delete this recipe?")
            setPositiveButton("Yes"){_,_ ->
                viewModel.deleteRecipe(currentRecipe)
                view?.findNavController()?.navigate(R.id.action_updateRecipesFragment_to_homeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

}