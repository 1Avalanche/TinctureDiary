package com.avalanche.android.tincturediary.ui.screens.listRecipeScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.avalanche.android.tincturediary.database.RecipeDatabase
import com.avalanche.android.tincturediary.database.RecipeRepository
import java.text.SimpleDateFormat

class ListRecipeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
    }

    //Data from DB
    val allNotFinishedRecipies = repository.allNotFinishedRecipies ?: MutableLiveData(emptyList())

    var currentTime = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

}