package com.adrianhelo.culinaryrecipes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adrianhelo.culinaryrecipes.domain.model.RecipeModel

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeModel)
    @Update
    suspend fun updateRecipe(recipe: RecipeModel)
    @Delete
    suspend fun deleteRecipe(recipe: RecipeModel)
    @Query("SELECT * FROM recipes_table ORDER BY id DESC")
    fun getAllRecipes(): LiveData<List<RecipeModel>>
    @Query("SELECT * FROM recipes_table WHERE recipe_tittle LIKE:query OR recipe_body LIKE:query")
    fun getRecipe(query: String?): LiveData<List<RecipeModel>>
}