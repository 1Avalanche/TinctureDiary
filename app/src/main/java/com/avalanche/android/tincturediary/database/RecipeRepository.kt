package com.avalanche.android.tincturediary.database

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.avalanche.android.tincturediary.database.RecipeDatabase
import com.avalanche.android.tincturediary.database.dao.RecipeDao
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.model.Stage
import java.util.*

private const val DATABASE_NAME = "recipe-database"

class RecipeRepository (private val recipeDao: RecipeDao)  {

    val allRecipies: LiveData<List<RecipePreparation>> = recipeDao.getAllRecipies()
    val allNotFinishedRecipies: LiveData<List<RecipePreparation>>? = recipeDao.getNotFinishedRecipe(false)
    val allFinishedRecipies: LiveData<List<RecipePreparation>>? = recipeDao.getNotFinishedRecipe(true)

    @Suppress
    @WorkerThread
    suspend fun insertRecipe(recipe: RecipePreparation) {
        recipeDao.insertRecipe(recipe)
    }

    @Suppress
    @WorkerThread
    suspend fun updateRecipe(recipe: RecipePreparation) {
        recipeDao.updateRecipe(recipe)
    }

    @Suppress
    @WorkerThread
    suspend fun deleteRecipe(recipe: RecipePreparation) {
        recipeDao.deleteRecipe(recipe)
    }

    fun getOneRecipe(id: UUID) : LiveData<RecipePreparation> {
        return recipeDao.getOneRecipe(id)
    }

}
