package com.avalanche.android.tincturediary.ui.screens.cookBookScreen

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import com.avalanche.android.tincturediary.database.RecipeDatabase
import com.avalanche.android.tincturediary.database.RecipeRepository

class CookBookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
    }

    val allFinishedRecepies = repository.allNotFinishedRecipies
}