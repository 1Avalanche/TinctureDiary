package com.avalanche.android.tincturediary.ui.screens.editRecipe

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.ui.components.IngredientView


class EditRecipeScreenViewModel : ViewModel() {

    private var _baseCouter: MutableLiveData<Int> = MutableLiveData(1)
    var baseCounter: LiveData<Int> = _baseCouter

    private var _ingrCouter: MutableLiveData<Int> = MutableLiveData(1)
    var ingrCounter: LiveData<Int> = _ingrCouter

    private var _stageCouter: MutableLiveData<Int> = MutableLiveData(1)
    var stageCounter: LiveData<Int> = _stageCouter

    var ingrList = MutableLiveData(mutableListOf(Ingredient("", "")))
//    var ingrMap = mutableMapOf<String, String>()

    fun addBase() {
        var newCount = baseCounter.value as Int
        newCount++
        _baseCouter.postValue(newCount)
    }

    fun addIngr() {
        ingrList.value!!.add(Ingredient("", ""))
        var newCount = ingrCounter.value as Int
        newCount++
        _ingrCouter.postValue(newCount)
    }

    fun removeIngr(index: Int) { //TODO//
//        ingrList.value!!.removeIf { it.title == title }
        var newIngrList = ingrList.value!!
        newIngrList.removeAt(index)
        ingrList.postValue(newIngrList)
        var newCount = newIngrList.size
//        ingrList.value!!.removeAt(index)
//        var newCount = ingrList.value!!.size
        ingrList.postValue(newIngrList)
        _ingrCouter.postValue(newCount)
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

//    fun save() {
//        for ((k,v) in ingrMap) {
//            var i = Ingredient(k,v)
//            ingrList.add(i)
//        }
//    }

//    fun takeIngredients(title:String, weight: String) {
//        var newIngr = Ingredient(title, weight)
//        ingrList.value!!.add(newIngr)
//        Log.d("ING", "Ing $title was added")
//
//    }


}