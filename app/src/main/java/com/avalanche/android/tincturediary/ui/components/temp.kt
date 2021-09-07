package com.avalanche.android.tincturediary.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.ui.screens.editRecipe.EditRecipeScreenViewModel

//import android.util.Log
//import android.widget.Toast
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.avalanche.android.tincturediary.model.AlcoholBase
//import com.avalanche.android.tincturediary.model.Ingredient
//import com.avalanche.android.tincturediary.model.RecipePreparation
//import com.avalanche.android.tincturediary.model.Stage
//import com.avalanche.android.tincturediary.ui.components.IngredientView
//
//
//class EditRecipeScreenViewModel : ViewModel() {
//
//    var emptyBase = AlcoholBase("", "", "")
//    var testBase1 = AlcoholBase("vodka", "100", "40")
//    var testBase2 = AlcoholBase("ethanol", "500", "96")
//    var emptyIngredient = Ingredient("", "")
//    var testIngr1 = Ingredient("вишня", "1кг")
//    var testIngr2 = Ingredient("корица", "1 палка")
//    var testIngr3 = Ingredient("сахар", "300гр")
//    var testIngr4 = Ingredient("вода", "500 мл")
//    var testDesc1 = "вишню положить в банку и залить спиртом"
//    var testDesc2 = "спирт слить в отдельную емкость, засыпать вишню сахаром и оставить на 7 дней, периодически встряхивать"
//    var testStage1 = Stage(1, mutableListOf(testIngr1, testIngr2), testDesc1, "")
//    var testStage2 = Stage(2, mutableListOf(testIngr3, testIngr4), testDesc2, "")
//    var testRecipe1 = RecipePreparation(
//        456,
//        "Вишня на спирту",
//        mutableListOf(testBase1, testBase2),
//        mutableListOf(testStage1, testStage2),
//        false
//    )
//    val emptyRecipe = RecipePreparation(
//        123, //   TODO  // как задавать автоматический ид?
//        "",
//        mutableListOf(emptyBase),
//        mutableListOf(Stage(1, mutableListOf(emptyIngredient), "", "")),
//        false
//    )
//    var fullRecipe = testRecipe1 // test
//    private val _recipe: MutableLiveData<RecipePreparation> = MutableLiveData(fullRecipe)
//    val recipe = _recipe
//
//    private val _basesList: MutableLiveData<MutableList<AlcoholBase>> = MutableLiveData(fullRecipe.listOfAlcoholBase)
//    val basesList: LiveData<MutableList<AlcoholBase>> = _basesList
//    private val _baseCouter: MutableLiveData<Int> = MutableLiveData(basesList.value!!.size)
//    val baseCounter: LiveData<Int> = _baseCouter
//
//    private val _stagesList: MutableLiveData<MutableList<Stage>> = MutableLiveData(fullRecipe.listOfStages)
//    val stagesList = _stagesList
//    private val _stageCouter: MutableLiveData<Int> = MutableLiveData(stagesList.value!!.size)
//    val stageCounter: LiveData<Int> = _stageCouter
//
//    //    private val _stagesMap: MutableLiveData<MutableMap<Int, MutableList<Ingredient>>> = MutableLiveData(makeStagesMap())
////    val stagesMap: LiveData<MutableMap<Int, MutableList<Ingredient>>> = _stagesMap
//    var stagesMap = makeStagesMap()
//
////    private val _ingrList = MutableLiveData(mutableListOf(
////        emptyIngredient
////    ))
////    val ingrList = _ingrList
////    private val _ingrCouter: MutableLiveData<Int> = MutableLiveData(ingrList.value!!.size)
////    val ingrCounter: LiveData<Int> = _ingrCouter
//
//
//    fun addBase() {
//        basesList.value!!.add(emptyBase)
//        _basesList.postValue(basesList.value)
//        var newCount = baseCounter.value as Int
//        newCount++
//        _baseCouter.postValue(newCount)
//
//    }
//
//    fun removeBase() {
//        basesList.value!!.removeAt(basesList.value!!.lastIndex)
//        var newCount = _baseCouter.value!!
//        newCount--
//        _basesList.postValue(basesList.value)
//        _baseCouter.postValue(newCount)
//    }
//
//    fun addStage() {
//        var newCount = stageCounter.value as Int
//        newCount++
//        _stageCouter.postValue(newCount)
//        stagesList.value!!.add(Stage(newCount, mutableListOf(emptyIngredient), "", ""))
//        _stagesList.postValue(stagesList.value)
//    }
//
//    fun removeStage() {
//        if (stageCounter.value == 0) return
//        else {
//            var newCount = stageCounter.value as Int
//            newCount--
//            _stageCouter.postValue(newCount)
//            stagesList.value!!.removeAt(stagesList.value!!.lastIndex)
//            _stagesList.postValue(stagesList.value)
//        }
//    }
//
//    fun makeStagesMap() : MutableMap<Int, MutableList<Ingredient>> {
//        var stagesMap: MutableMap<Int, MutableList<Ingredient>> = mutableMapOf(fullRecipe.listOfStages.size to fullRecipe.listOfStages[0].listOfIngredients )
//        for(stage in fullRecipe.listOfStages)  {
//            stagesMap.put(stage.number, stage.listOfIngredients)
//        }
//        return stagesMap
//    }
//
//    fun takeIngrListLiveData(stageNum: Int) : LiveData<MutableList<Ingredient>> {
//        var _ingrList: MutableLiveData<MutableList<Ingredient>> = MutableLiveData(stagesMap[stageNum])
//        var ingrList: LiveData<MutableList<Ingredient>> = _ingrList
//        return ingrList
//    }
//
//    fun takeIngrCountLiveData(stageNum: Int) : LiveData<Int> {
//        var _ingrCount: MutableLiveData<Int> = MutableLiveData(stagesMap[stageNum]!!.size)
//        var ingrCount: LiveData<Int> = _ingrCount
//        return ingrCount
//    }
//
//    fun addIngr(stageNum: Int) {
//        recipe.value!!.listOfStages[stageNum-1].listOfIngredients.add(emptyIngredient)
//        _recipe.postValue(recipe.value)
//        takeIngrListLiveData(stageNum)
//        takeIngrCountLiveData(stageNum)
//
////        ingrList.value!!.add(emptyIngredient)
////        var newCount = ingrCounter.value as Int
////        newCount++
////        _ingrCouter.postValue(newCount)
//
//    }
//
//
//    fun removeIngr() {
////        ingrList.value!!.removeAt(ingrList.value!!.lastIndex)
////        var newCount = _ingrCouter.value!!
////        newCount--
////        _ingrList.postValue(ingrList.value)
////        _ingrCouter.postValue(newCount)
//    }
//
//
//
//
////    fun save() {
////        for ((k,v) in ingrMap) {
////            var i = Ingredient(k,v)
////            ingrList.add(i)
////        }
////    }
//
////    fun takeIngredients(title:String, weight: String) {
////        var newIngr = Ingredient(title, weight)
////        ingrList.value!!.add(newIngr)
////        Log.d("ING", "Ing $title was added")
////
////    }
//
//
//}


//fun StageView(
//    viewModel: EditRecipeScreenViewModel,
//    num: Int,
//    ingList: List<Ingredient>,
//    description: String,
//    expDate: String,
//    isRemovable: Boolean) {
//
////    val ingredients by viewModel.ingrCounter.observeAsState()
//    var description by remember { mutableStateOf(description) }
////    var ingrList by remember { mutableStateOf(ingList)}
////    val stagesMap by viewModel.stagesMap.observeAsState()
////    var ingredients by remember {mutableStateOf(stagesMap!![num]!!.size)}
////    var ingrList by remember { mutableStateOf(stagesMap!![num])}
//    var number by remember { mutableStateOf(num) }
//    val ingrList by viewModel.takeIngrListLiveData(number).observeAsState()
//    val ingredients by viewModel.takeIngrCountLiveData(number).observeAsState()
//
//
//    Column {
//        Row(modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween  ) {
//            Text("Фаза $num")
//            Button(onClick = { viewModel.removeStage() },
//                enabled = isRemovable) {
//                Text("X")
//            }
//        }
//        Divider(
//            thickness = 0.5.dp,
//            color = MaterialTheme.colors.secondaryVariant
//        )
//
//        Text("Ингредиенты")
//        for (i in 0 until ingredients!! ) {
//            var isRemovable = if (i == ingredients!!-1) true else false
//            IngredientView(
//                viewModel,
//                ingrList!![i].title ,
//                ingrList!![i].weight,
//                i,
//                isRemovable)
//        }
//        Row(modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Button(onClick = {
//                viewModel.addIngr(number)
//                Log.d("ADD", "Recipe, stage 1, ingr list size ${viewModel.recipe.value!!.listOfStages[0].listOfIngredients.size} ")
//            } ) {
//                Text("Добавить ингредиент")
//            }
//        }
//        Text("Описание")
//        Row(modifier = Modifier
//            .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically) {
//            TextField(
//                value = description,
//                onValueChange = {description = it},
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(4.dp),
//                placeholder = { Text("введите описание приготовления") }
//            )
//        }
//        Row(modifier = Modifier
//            .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween) {
//            Text("Дата окончания")
//            Box(modifier = Modifier
//                .size(20.dp)
//                .background(MaterialTheme.colors.onError, RectangleShape))
//        }
//    }
//}

