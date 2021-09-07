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
    var testBase1 = AlcoholBase("vodka", "100", "40")
    var testBase2 = AlcoholBase("ethanol", "500", "96")
    var emptyIngredient = Ingredient("", "")
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
    val emptyRecipe = RecipePreparation(
        123, //   TODO  // как задавать автоматический ид?
        "",
        mutableListOf(emptyBase),
        mutableListOf(Stage(1, mutableListOf(emptyIngredient), "", "")),
        false
    )

    val fullRecipe = testRecipe1
    val _recipe: MutableLiveData<RecipePreparation> = MutableLiveData(fullRecipe)
    val recipe: LiveData<RecipePreparation> = _recipe
    val finalRecipe: RecipeForSave = copyRecipe(fullRecipe)

    private val _basesList: MutableLiveData<List<AlcoholBase>> =
        MutableLiveData(recipe.value!!.listOfAlcoholBase)
    val basesList: LiveData<List<AlcoholBase>> = _basesList
    private val _baseCouter: MutableLiveData<Int> = MutableLiveData(basesList.value!!.size)
    val baseCounter: LiveData<Int> = _baseCouter

    private val _stagesList: MutableLiveData<List<Stage>> =
        MutableLiveData(recipe.value!!.listOfStages)
    val stagesList = _stagesList
    private val _stageCouter: MutableLiveData<Int> = MutableLiveData(stagesList.value!!.size)
    val stageCounter: LiveData<Int> = _stageCouter

    var _stagesMap: MutableLiveData<MutableMap<Int, List<Ingredient>>> =
        MutableLiveData(makeStagesMap())
    val stagesMap: LiveData<MutableMap<Int, List<Ingredient>>> = _stagesMap

    //для записи ингредиентов в рецепт: создать изменяемый финишный рецепт, в который значения только записываются
    //или все-таки мапу??

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
        tempList.removeAt(tempList.lastIndex)
        var newBaseList: List<AlcoholBase> = tempList
        var newRecipe = recipe.value!!.copy(listOfAlcoholBase = newBaseList)
        _recipe.value = newRecipe
    }

    fun replaseBaseName(index: Int, title: String, volume: String, strength: String) {
        var newBase = AlcoholBase(title, volume, strength)
        finalRecipe.listOfAlcoholBase[index] = newBase
    }



    fun addStage() {
//        var newCount = stageCounter.value as Int
//        newCount++
//        _stageCouter.postValue(newCount)
//        stagesList.value!!.add(Stage(newCount, mutableListOf(emptyIngredient), "", ""))
//        _stagesList.postValue(stagesList.value)
//        finalRecipe.listOfStages.add(Stage(newCount, mutableListOf(emptyIngredient), "", ""))
    }

    fun removeStage() {
//        if (stageCounter.value == 0) return
//        else {
//            var newCount = stageCounter.value as Int
//            newCount--
//            _stageCouter.postValue(newCount)
//            stagesList.value!!.removeAt(stagesList.value!!.lastIndex)
//            _stagesList.postValue(stagesList.value)
//            finalRecipe.listOfStages.removeAt(finalRecipe.listOfStages.lastIndex)
//        }
    }

    fun makeStagesMap(): MutableMap<Int, List<Ingredient>> {
        var currentRecipe = recipe.value!!
        var stagesMap: MutableMap<Int, List<Ingredient>> =
            mutableMapOf(currentRecipe.listOfStages[0].number to currentRecipe.listOfStages[0].listOfIngredients)
        for (stage in currentRecipe.listOfStages) {
            stagesMap.put(stage.number, stage.listOfIngredients)
        }
        return stagesMap
    }

//    fun takeIngrListLiveData(stageNum: Int) : LiveData<MutableList<Ingredient>> {
//        var _ingrList: MutableLiveData<MutableList<Ingredient>> = MutableLiveData(stagesMap.value!![stageNum])
//        var ingrList: LiveData<MutableList<Ingredient>> = _ingrList
//        return ingrList
//    }
//
//    fun takeIngrCountLiveData(stageNum: Int) : LiveData<Int> {
//        var _ingrCount: MutableLiveData<Int> = MutableLiveData(stagesMap.value!![stageNum]!!.size)
//        var ingrCount: LiveData<Int> = _ingrCount
//        return ingrCount
//    }

    fun addIngr(stageNum: Int) {
//        recipe.value!!.listOfStages[stageNum-1].listOfIngredients.add(emptyIngredient)
//        _recipe.postValue(recipe.value)
//        _stagesMap.postValue(makeStagesMap())
    }


    fun removeIngr() {

    }

//    fun collectIngrs(stageNum: Int, index: Int, title: String, weight: String) {
//        var iList = mutableListOf<Ingredient>(emptyIngredient)
//        iList[index].title = title
//        iList[index].weight = weight
//        var finalMap = mutableMapOf<Int, MutableList<Ingredient>>(stageNum to iList)
//    }
}