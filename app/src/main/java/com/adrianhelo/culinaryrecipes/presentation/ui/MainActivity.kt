package com.adrianhelo.culinaryrecipes.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.adrianhelo.culinaryrecipes.R
import com.adrianhelo.culinaryrecipes.data.local.AppDatabase
import com.adrianhelo.culinaryrecipes.databinding.ActivityMainBinding
import com.adrianhelo.culinaryrecipes.domain.repository.RecipesRepository
import com.adrianhelo.culinaryrecipes.presentation.viewmodel.RecipeViewModel
import com.adrianhelo.culinaryrecipes.presentation.viewmodel.RecipeViewModelFactory
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val recipesRepository = RecipesRepository(AppDatabase(this))
        val viewModelFactory = RecipeViewModelFactory(recipesRepository, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipeViewModel::class.java)
    }
}