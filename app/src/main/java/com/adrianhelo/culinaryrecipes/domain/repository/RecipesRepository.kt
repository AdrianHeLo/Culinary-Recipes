package com.adrianhelo.culinaryrecipes.domain.repository

import com.adrianhelo.culinaryrecipes.data.local.AppDatabase
import com.adrianhelo.culinaryrecipes.data.local.RecipesDao
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel

class RecipesRepository (private val database: AppDatabase){
    fun getAllRecipes() = database.recipeDao().getAllRecipes()
    fun getRecipe(query: String?) = database.recipeDao().getRecipe(query)
    suspend fun insertRecipe(recipe: RecipeModel) = database.recipeDao().insertRecipe(recipe)
    suspend fun updateRecipe(recipe: RecipeModel) = database.recipeDao().updateRecipe(recipe)
    suspend fun deleteRecipe(recipe: RecipeModel) = database.recipeDao().deleteRecipe(recipe)
}