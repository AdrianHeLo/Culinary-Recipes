package com.adrianhelo.culinaryrecipes.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel
import com.adrianhelo.culinaryrecipes.domain.repository.RecipesRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipesRepository, application: Application): AndroidViewModel(application) {
    fun getAllRecipes() = repository.getAllRecipes()
    fun getRecipe(query: String?) = repository.getRecipe(query)
    fun insertRecipe(recipe: RecipeModel) = viewModelScope.launch {
        repository.insertRecipe(recipe)
    }
    fun deleteRecipe(recipe: RecipeModel) = viewModelScope.launch {
        repository.deleteRecipe(recipe)
    }
    fun updateRecipe(recipe: RecipeModel) = viewModelScope.launch {
        repository.updateRecipe(recipe)
    }
}