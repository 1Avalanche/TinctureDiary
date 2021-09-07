package com.avalanche.android.tincturediary.ui.screens.editRecipe

import android.util.Log
import android.widget.CheckBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import com.avalanche.android.tincturediary.ui.components.TitleField
import com.avalanche.android.tincturediary.ui.components.StageView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.LiveData
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.ui.components.AlcoholBaseView
import java.sql.Wrapper

@ExperimentalFoundationApi
@Composable
fun EditRecipeScreen(editRecipeScreenViewModel: EditRecipeScreenViewModel = viewModel()) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        val recipe by editRecipeScreenViewModel.recipe.observeAsState()
        val stagesList by editRecipeScreenViewModel.stagesList.observeAsState()
        val stages by editRecipeScreenViewModel.stageCounter.observeAsState()
        var text by remember { mutableStateOf(recipe!!.title) }
//        var counter by remember { mutableStateOf(0) }

        LazyColumn(
            modifier = Modifier.fillMaxSize())
        {
            stickyHeader {
                Toolbar(editRecipeScreenViewModel)
            }
            item {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it
                    },
                    label = { Text("Название") } )
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Text("Спиртовая основа")
                }
            }
            item{
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center) {
                    for (i in 0 until recipe!!.listOfAlcoholBase.size) {
                        var isRemovable = if (i == recipe!!.listOfAlcoholBase.size - 1) true else false
                        AlcoholBaseView(
                            editRecipeScreenViewModel,
                            recipe!!.listOfAlcoholBase!![i].title,
                            recipe!!.listOfAlcoholBase!![i].volume!!,
                            recipe!!.listOfAlcoholBase!![i].strength!!,
                            i,
                            isRemovable
                        )
                    }
                }
            }
            item{
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        editRecipeScreenViewModel.addBase()
                        Log.d("BASE", "Recipe bases is ${recipe!!.listOfAlcoholBase.size}")
//                        counter++
                    } ) {
                        Text("Добавить основу")
                    }
                }
            }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {//problem
                    for (i in 0 until stages!!) {
                        var isRemovable = if (i == stages!! - 1) false else true
                        StageView(
                            editRecipeScreenViewModel,
                            i + 1,
                            stagesList!![i].description,
                            stagesList!![i].expirationDate,
                            isRemovable
                        )
                    }
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        editRecipeScreenViewModel.addStage()
                    } ) {
                        Text("Добавить фазу")
                    }
                }
            }
            item {isFinished()}
        }
    }

}

@Composable
fun Toolbar(editRecipeScreenViewModel: EditRecipeScreenViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {/*TODO*/ }) {
            Text("Prev")
        }
        Button(onClick = {
            var str = ""
            for (base in editRecipeScreenViewModel.finalRecipe.listOfAlcoholBase) {
                str = str + base.title + ", "
            }
//            Log.d("ING", "stagemap, s1, ings is ${editRecipeScreenViewModel.stagesMap.value!![1]!![0].title} ${editRecipeScreenViewModel.stagesMap.value!![1]!![1].title} ${editRecipeScreenViewModel.stagesMap.value!![1]!![2].title}")
            Log.d("ING", "FULL Rp title ${editRecipeScreenViewModel.fullRecipe.title}, Bases  ${editRecipeScreenViewModel.recipe.value!!.listOfAlcoholBase.size} , AB1: ${editRecipeScreenViewModel.recipe.value!!.listOfAlcoholBase[0].title} - AB2 ${editRecipeScreenViewModel.recipe.value!!.listOfAlcoholBase[1].title} ")
            Log.d("ING", "FINAL Rp title ${editRecipeScreenViewModel.finalRecipe.title}, Bases ${editRecipeScreenViewModel.finalRecipe.listOfAlcoholBase.size} bases: $str " )
            })
        { Text(text = "Save")
        }
    }
}



@Composable
fun isFinished() {
        var onCheckedChange: ((Boolean) -> Unit)? = null
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Напиток закончен")
            Checkbox(
                checked = false,
                onCheckedChange = onCheckedChange
            )
        }
    }

