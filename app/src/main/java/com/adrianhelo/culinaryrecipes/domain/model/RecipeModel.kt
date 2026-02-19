package com.adrianhelo.culinaryrecipes.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipes_table")
@Parcelize
data class RecipeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "recipe_tittle")
    val recipeTittle: String,
    @ColumnInfo(name = "recipe_body")
    val recipeBody: String) : Parcelable
