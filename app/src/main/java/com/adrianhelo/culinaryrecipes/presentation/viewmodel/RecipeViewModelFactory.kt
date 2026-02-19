package com.adrianhelo.culinaryrecipes.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrianhelo.culinaryrecipes.domain.repository.RecipesRepository

class RecipeViewModelFactory (private val repository: RecipesRepository, private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(repository, application) as T
    }
}