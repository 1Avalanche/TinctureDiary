package com.avalanche.android.tincturediary.ui.screens.editRecipe

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avalanche.android.tincturediary.model.Ingredient


class EditRecipeScreenViewModel : ViewModel() {

    private var _baseCouter: MutableLiveData<Int> = MutableLiveData(1)
    var baseCounter: LiveData<Int> = _baseCouter

    private var _ingrCouter: MutableLiveData<Int> = MutableLiveData(1)
    var ingrCounter: LiveData<Int> = _ingrCouter

    private var _stageCouter: MutableLiveData<Int> = MutableLiveData(1)
    var stageCounter: LiveData<Int> = _stageCouter

    var ingrList = mutableListOf<Ingredient>()

    fun addBase() {
        var newCount = baseCounter.value as Int
        newCount++
        _baseCouter.postValue(newCount)
    }

    fun addIngr() {
        var newCount = ingrCounter.value as Int
        newCount++
        _ingrCouter.postValue(newCount)
    }

    fun removeIngr() {
        if (ingrCounter.value == 0) return
        else {
            var newCount = ingrCounter.value as Int
            newCount--
            _ingrCouter.postValue(newCount)
        }
    }

    fun addStage() {
        var newCount = stageCounter.value as Int
        newCount++
        _stageCouter.postValue(newCount)
    }
    fun removeStage() {
        if (stageCounter.value == 0) return
        else {
            var newCount = stageCounter.value as Int
            newCount--
            _stageCouter.postValue(newCount)
        }
    }

//    fun takeIngredients(title:String, weight: String) : List<Ingredient> {
//        var newIngr = Ingredient(title, weight)
//        ingrList.add(newIngr)
//        Log.d("INGR", "Last ingr is ${ingrList[ingrList.lastIndex].title}")
//        return ingrList
//
//    }

    fun takeIngredients(i: Ingredient) {
        ingrList.add(i)
        Log.d("INGR", "Last ingr is ${ingrList[ingrList.lastIndex].title}")
        return
    }



}