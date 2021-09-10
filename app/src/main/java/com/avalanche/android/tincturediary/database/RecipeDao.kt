package com.avalanche.android.tincturediary.database

import android.content.IntentSender
import androidx.lifecycle.LiveData
import androidx.room.*
import com.avalanche.android.tincturediary.model.RecipePreparation

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipePreparation)

    @Update
    suspend fun updateRecipe(recipe: RecipePreparation)

    @Delete
    suspend fun deleteRecipe(recipe: RecipePreparation)

    @Query("SELECT * FROM RecipePreparation")
    fun getAllRecipies() : LiveData<List<RecipePreparation>>

    @Query("SELECT * FROM RecipePreparation WHERE id=(:id)")
    fun getOneRecipe(id: Int) : LiveData<RecipePreparation>?

    @Query("SELECT * FROM RecipePreparation WHERE isFinished=(:isFinished)")
    fun getNotFinishedRecipe(isFinished: Boolean) : LiveData<List<RecipePreparation>>?

}
