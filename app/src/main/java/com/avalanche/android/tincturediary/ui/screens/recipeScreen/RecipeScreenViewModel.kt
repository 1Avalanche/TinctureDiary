package com.avalanche.android.tincturediary.ui.screens.recipeScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.avalanche.android.tincturediary.database.RecipeDatabase
import com.avalanche.android.tincturediary.database.RecipeRepository
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.model.Stage
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

//class for finish pecipe, to store in DB
data class RecipeForSave(
    var id: UUID,
    var title: String,
    var listOfAlcoholBase: MutableList<AlcoholBase>,
    var listOfStages: MutableList<Stage>,
    var isFinished: Boolean,
)

//This view model class for 2 screens = AddScreen and EditScreen.
class RecipeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
    }

        //empty components
    var emptyBase = AlcoholBase("", "", "")
    var emptyIngredient = Ingredient("", "")
    var emptyStage = Stage(0, listOf(emptyIngredient), "", "")
    val emptyRecipe = RecipePreparation(
        UUID.randomUUID(),
        "",
        mutableListOf(emptyBase),
        mutableListOf(Stage(1, mutableListOf(emptyIngredient), "", "")),
        false
    )

        //Data retrieval from DB
    val allRecipiesLivaData = repository.allRecipies
    val idLiveData: MutableLiveData<UUID> = MutableLiveData()
    val recipeLiveData: LiveData<RecipePreparation> =
        Transformations.switchMap(idLiveData) { recipeID ->
            repository.getOneRecipe(recipeID)
        }


    var fullRecipe = emptyRecipe //Start recipe. May be empty of test recipe.
    val _recipe: MutableLiveData<RecipePreparation> = MutableLiveData(fullRecipe)
    val recipe: LiveData<RecipePreparation> = _recipe
    var finalRecipe: RecipeForSave = copyRecipe(recipe.value!!) //recipe to collect and save changes

        //LiveData change when Screen send a new ID, and view model refresh recipe to observe
    val _recipeRefreshed: MutableLiveData<Boolean> = MutableLiveData(false)
    val recipeRefreshed: LiveData<Boolean> = _recipeRefreshed

    var isRecipeFinished = false

//    var currentTime = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

    fun takeNewId(id: UUID) {
        idLiveData.postValue(id)
    }

    var callCounter = 0 // this fun make call one time for one screen
    fun refreshVM(rp: RecipePreparation)  {
        if (callCounter > 0) {
            return
        }
        _recipe.postValue(rp)
        fullRecipe = rp.copy()
        finalRecipe = copyRecipe(rp)
        _recipeRefreshed.postValue(true)
        callCounter++
    }

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
        finalRecipe.listOfStages.removeAt(finalRecipe.listOfStages.lastIndex)
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

    fun collectExpDate(stageNum: Int, newDate: String) {
        var newStage = finalRecipe.listOfStages[stageNum-1].copy(expirationDate = newDate)
        finalRecipe.listOfStages[stageNum-1] = newStage
    }

    //Take recipe to store (finalRecipe) and save it as @Entity type
    private fun generateRecipeToStore() : RecipePreparation {
        var basesList: List<AlcoholBase> = finalRecipe.listOfAlcoholBase
        var stagesList: List<Stage> = finalRecipe.listOfStages
        var newRecipe = RecipePreparation(
            finalRecipe.id,
            finalRecipe.title,
            basesList,
            stagesList,
            isRecipeFinished)
        return newRecipe
    }

    //insert to DB
    fun insertRp() {
        val recipeToStore = generateRecipeToStore()
        viewModelScope.launch {
            repository.insertRecipe(recipeToStore)
        }
    }

    //TEST

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
            UUID.randomUUID(),
            "Вишня на спирту",
            listOf(testBase1, testBase2),
            listOf(testStage1, testStage2),
            false
        )
        return testRecipe1
    }
}

