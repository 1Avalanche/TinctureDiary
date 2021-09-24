package com.avalanche.android.tincturediary.ui.screens.readyRecipeScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.avalanche.android.tincturediary.database.RecipeDatabase
import com.avalanche.android.tincturediary.database.RecipeRepository
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.model.Stage
import java.util.*

class ReadyRecipeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
    }

    private val idLiveData: MutableLiveData<UUID> = MutableLiveData()
    val recipeLiveData: LiveData<RecipePreparation> =
        Transformations.switchMap(idLiveData) { recipeID ->
            repository.getOneRecipe(recipeID)
        }

    private val _listOfAlcoholBase: MutableLiveData<List<AlcoholBase>> = MutableLiveData()
    val listOfAlcoholBase: LiveData<List<AlcoholBase>> = _listOfAlcoholBase

    private val _listOfStages: MutableLiveData<List<Stage>> = MutableLiveData()
    val listOfStages: LiveData<List<Stage>> = _listOfStages

    fun takeNewId(id: UUID) {
        idLiveData.postValue(id)
        refreshValues()
    }

    fun refreshValues() {
        if (recipeLiveData.value != null) {
            _listOfAlcoholBase.postValue(recipeLiveData.value!!.listOfAlcoholBase)
            _listOfStages.postValue(recipeLiveData.value!!.listOfStages)
        }
    }

}