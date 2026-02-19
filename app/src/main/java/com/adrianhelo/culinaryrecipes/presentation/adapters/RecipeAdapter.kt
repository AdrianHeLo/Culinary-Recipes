package com.adrianhelo.culinaryrecipes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.adrianhelo.culinaryrecipes.databinding.RecipesItemLayoutBinding
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel
import com.adrianhelo.culinaryrecipes.presentation.ui.fragments.HomeFragmentDirections

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(val itemBinding: RecipesItemLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<RecipeModel>(){
        override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.recipeTittle == newItem.recipeTittle &&
                    oldItem.recipeBody == newItem.recipeBody
        }
        override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: RecipesItemLayoutBinding = DataBindingUtil.inflate(inflater, com.adrianhelo.culinaryrecipes.R.layout.recipes_item_layout, parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemBinding.tittleRecipesItem.text = item.recipeTittle
        holder.itemBinding.descriptionRecipesItem.text = item.recipeBody
        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateRecipesFragment(item)
            it.findNavController().navigate(direction)
        }
    }

}