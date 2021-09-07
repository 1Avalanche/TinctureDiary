package com.avalanche.android.tincturediary.ui.screens.editRecipe

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.model.Stage
import com.avalanche.android.tincturediary.ui.components.IngredientView
import kotlinx.coroutines.delay


data class RecipeForSave(
    var id: Int,
    var title: String,
    var listOfAlcoholBase: MutableList<AlcoholBase>,
    var listOfStages: MutableList<Stage>,
    var isFinished: Boolean,
)

class EditRecipeScreenViewModel : ViewModel() {

    var emptyBase = AlcoholBase("", "", "")
    var emptyIngredient = Ingredient("", "")
    var emptyStage = Stage(0, listOf(emptyIngredient), "", "")
    val emptyRecipe = RecipePreparation(
        123, //   TODO  // как задавать автоматический ид?
        "",
        mutableListOf(emptyBase),
        mutableListOf(Stage(1, mutableListOf(emptyIngredient), "", "")),
        false
    )


    var testRecipe1 = testRp()

    val fullRecipe = testRecipe1
    val _recipe: MutableLiveData<RecipePreparation> = MutableLiveData(fullRecipe)
    val recipe: LiveData<RecipePreparation> = _recipe
    val finalRecipe: RecipeForSave = copyRecipe(fullRecipe)

    var baseSizeCounter = recipe.value!!.listOfAlcoholBase.size

    var isFinished = false

    private fun copyRecipe(rp: RecipePreparation) : RecipeForSave {
        var baseList: MutableList<AlcoholBase> = mutableListOf()
        baseList.addAll(rp.listOfAlcoholBase)
        var stagesList: MutableList<Stage> = mutableListOf()
        stagesList.addAll(rp.listOfStages)
        return RecipeForSave(rp.id, rp.title, baseList, stagesList, rp.isFinished)
    }

    fun addBase() {
        finalRecipe.listOfAlcoholBase.add(emptyBase)
        val tempList: MutableList<AlcoholBase> = mutableListOf()
        tempList.addAll(recipe.value!!.listOfAlcoholBase)
        tempList.add(emptyBase)
        var newBaseList: List<AlcoholBase> = tempList
        var newRecipe = recipe.value!!.copy(listOfAlcoholBase = newBaseList)
        _recipe.value = newRecipe
    }

    fun removeBase() {
        finalRecipe.listOfAlcoholBase.removeAt(finalRecipe.listOfAlcoholBase.lastIndex)
        val tempList: MutableList<AlcoholBase> = mutableListOf()
        tempList.addAll(recipe.value!!.listOfAlcoholBase)
        tempList.removeLast()
        var newBaseList: List<AlcoholBase> = tempList
        var newRecipe = recipe.value!!.copy(listOfAlcoholBase = newBaseList)
        _recipe.value = newRecipe
    }

    fun collectBaseToFinal(index: Int, title: String, volume: String, strength: String) {
        var newBase = AlcoholBase(title, volume, strength)
        finalRecipe.listOfAlcoholBase[index] = newBase
    }

    fun addStage() {
        finalRecipe.listOfStages.add(emptyStage)
        val tempList: MutableList<Stage> = mutableListOf()
        tempList.addAll(recipe.value!!.listOfStages)
        tempList.add(emptyStage)
        var newStageList: List<Stage> = tempList
        var newRecipe = recipe.value!!.copy(listOfStages = newStageList)
        _recipe.value = newRecipe
    }

    fun removeStage() {
        finalRecipe.listOfAlcoholBase.removeAt(finalRecipe.listOfAlcoholBase.lastIndex)
        val tempList: MutableList<Stage> = mutableListOf()
        tempList.addAll(recipe.value!!.listOfStages)
        tempList.removeLast()
        var newStageList: List<Stage> = tempList
        var newRecipe = recipe.value!!.copy(listOfStages = newStageList)
        _recipe.value = newRecipe
    }

    fun addIngr(stageNum: Int) {
        val tempIngrList: MutableList<Ingredient> = mutableListOf()
        tempIngrList.addAll(recipe.value!!.listOfStages[stageNum-1].listOfIngredients)
        tempIngrList.add(emptyIngredient)
        var newIngrList: List<Ingredient> = tempIngrList
        replaceIngrList(stageNum, newIngrList)
    }

    fun removeIngr(stageNum: Int) {
        val tempIngrList: MutableList<Ingredient> = mutableListOf()
        tempIngrList.addAll(recipe.value!!.listOfStages[stageNum-1].listOfIngredients)
        tempIngrList.removeLast()
        var newIngrList: List<Ingredient> = tempIngrList
        replaceIngrList(stageNum, newIngrList)
    }

    private fun replaceIngrList(stageNum: Int, newIngrList: List<Ingredient>) {
        var newStage = recipe.value!!.listOfStages[stageNum-1].copy(listOfIngredients = newIngrList)
        finalRecipe.listOfStages[stageNum-1] = newStage
        //made new stage and added into the final Rp
        val tempStagesList: MutableList<Stage> = mutableListOf()
        tempStagesList.addAll(recipe.value!!.listOfStages)
        tempStagesList[stageNum-1] = newStage
        var newStageList: List<Stage> = tempStagesList
        var newRecipe = recipe.value!!.copy(listOfStages = newStageList)
        _recipe.value = newRecipe
    }

    fun collectIngrsToFinal(stageNum: Int, index: Int, title: String, weight: String) {
        var newIngr = Ingredient(title, weight)
        val tempIngrList: MutableList<Ingredient> = mutableListOf()
        tempIngrList.addAll(finalRecipe.listOfStages[stageNum-1].listOfIngredients)
        tempIngrList[index] = newIngr
        var newIngrList: List<Ingredient> = tempIngrList
        var newStage = finalRecipe.listOfStages[stageNum-1].copy(listOfIngredients = newIngrList)
        finalRecipe.listOfStages[stageNum-1] = newStage
    }

    fun testRp() : RecipePreparation {
        var testBase1 = AlcoholBase("vodka", "100", "40")
        var testBase2 = AlcoholBase("ethanol", "500", "96")
        var testIngr1 = Ingredient("вишня", "1кг")
        var testIngr2 = Ingredient("корица", "1 палка")
        var testIngr3 = Ingredient("сахар", "300гр")
        var testIngr4 = Ingredient("вода", "500 мл")
        var testDesc1 = "вишню положить в банку и залить спиртом"
        var testDesc2 =
            "спирт слить в отдельную емкость, засыпать вишню сахаром и оставить на 7 дней, периодически встряхивать"
        var testStage1 = Stage(1, listOf(testIngr1, testIngr2), testDesc1, "")
        var testStage2 = Stage(2, listOf(testIngr3, testIngr4), testDesc2, "")
        var testRecipe1 = RecipePreparation(
            456,
            "Вишня на спирту",
            listOf(testBase1, testBase2),
            listOf(testStage1, testStage2),
            false
        )
        return testRecipe1
    }
}