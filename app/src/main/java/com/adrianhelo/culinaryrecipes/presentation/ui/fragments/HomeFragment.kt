package com.adrianhelo.culinaryrecipes.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adrianhelo.culinaryrecipes.R
import com.adrianhelo.culinaryrecipes.databinding.FragmentHomeBinding
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel
import com.adrianhelo.culinaryrecipes.presentation.adapters.RecipeAdapter
import com.adrianhelo.culinaryrecipes.presentation.ui.MainActivity
import com.adrianhelo.culinaryrecipes.presentation.viewmodel.RecipeViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var _homeBinding: FragmentHomeBinding ?= null
    private val homeBinding get() = _homeBinding!!
    private lateinit var viewModel: RecipeViewModel
    private var recipeAdapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        displayRecyclerView()
        homeBinding.addButtonHomeFragment.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_newRecipesFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        val searchMenu = menu.findItem(R.id.main_menu_search).actionView as SearchView
        searchMenu.isSubmitButtonEnabled = false
        searchMenu.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //getRecipe(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            getRecipe(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _homeBinding = null
    }

    private fun getRecipe(query: String?) {
        val searchQuery = "%$query"
        viewModel.getRecipe(searchQuery).observe(
            this
        ) { list -> recipeAdapter.differ.submitList(list) }
    }

    private fun displayRecyclerView() {
        recipeAdapter = RecipeAdapter()
        homeBinding.recyclerContainerHomeFragment.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = recipeAdapter
        }
        activity?.let {
            viewModel.getAllRecipes().observe(
                viewLifecycleOwner
            ) { recipe ->
                recipeAdapter.differ.submitList(recipe)
                updateUI(recipe)
            }
        }
    }

    private fun updateUI(recipe: List<RecipeModel>?){
        if (recipe != null){
            if (recipe.isNotEmpty()){
                homeBinding.cardviewContainerHomeFragment.visibility = View.GONE
                homeBinding.recyclerContainerHomeFragment.visibility = View.VISIBLE
            }else{
                homeBinding.cardviewContainerHomeFragment.visibility = View.VISIBLE
                homeBinding.recyclerContainerHomeFragment.visibility = View.GONE
            }
        }
    }

}
