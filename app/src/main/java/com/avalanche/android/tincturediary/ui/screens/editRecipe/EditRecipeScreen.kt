package com.avalanche.android.tincturediary.ui.screens.editRecipe

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun EditRecipeScreen(context: Context, editRecipeScreenViewModel: EditRecipeScreenViewModel = viewModel()) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        val recipe by editRecipeScreenViewModel.recipe.observeAsState()
        var text by remember { mutableStateOf(recipe!!.title) }
        var finalRecipe by remember { mutableStateOf(editRecipeScreenViewModel.finalRecipe) }

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
                editRecipeScreenViewModel.finalRecipe.title = text
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Text("Спиртовая основа")
                }
            }
            item{
                    for (i in 0 until recipe!!.listOfAlcoholBase.size) {
                        var isRemovable = if (i == recipe!!.listOfAlcoholBase.size - 1) true else false
                        AlcoholBaseView(
                            editRecipeScreenViewModel,
                            finalRecipe.listOfAlcoholBase[i]!!.title,
                            finalRecipe.listOfAlcoholBase[i]!!.volume,
                            finalRecipe.listOfAlcoholBase[i]!!.strength,
                            i,
                            isRemovable
                        )
                    }
            }
            item{
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        editRecipeScreenViewModel.addBase()
                    } ) {
                        Text("Добавить основу")
                    }
                }
            }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {//problem
                    for (i in 0 until recipe!!.listOfStages.size) {
                        var isRemovable = if (i == recipe!!.listOfStages.size - 1) false else true
                        StageView(
                            context,
                            editRecipeScreenViewModel,
                            i + 1,
                            recipe!!.listOfStages!![i].description,
                            recipe!!.listOfStages!![i].expirationDate,
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
            item {isFinished(editRecipeScreenViewModel)}
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
            editRecipeScreenViewModel.generateRecipeToStore()
            var bases = ""
            for (base in editRecipeScreenViewModel.finalRecipe.listOfAlcoholBase) {
                bases = bases + base.title + ", "
            }
            var s1i = ""
            for (ing in editRecipeScreenViewModel.finalRecipe.listOfStages[0].listOfIngredients) {
                s1i = s1i + ing.title + ", "
            }
            var s2i = ""
            for (ing in editRecipeScreenViewModel.finalRecipe.listOfStages[1].listOfIngredients) {
                s2i = s2i + ing.title + ", "
            }
            var fin = if(editRecipeScreenViewModel.isRecipeFinished) "is finished" else "is not finished"

            Log.d("FINAL", "FINAL Rp: ${editRecipeScreenViewModel.finalRecipe.title} " +
                    "\n has ${editRecipeScreenViewModel.finalRecipe.listOfAlcoholBase.size} alcohol bases:" +
                    "\n $bases." +
                    "\n and ${editRecipeScreenViewModel.finalRecipe.listOfStages.size} stages." +
                    "\n Stage 1 contains: $s1i," +
                    "\n with description: ${editRecipeScreenViewModel.finalRecipe.listOfStages[0].description}" +
                    "\n and exp.date is ${editRecipeScreenViewModel.finalRecipe.listOfStages[0].expirationDate}." +
                    "\n Stage 2 contains: $s2i," +
                    "\n with description: ${editRecipeScreenViewModel.finalRecipe.listOfStages[1].description}" +
                    "\n and exp.date is ${editRecipeScreenViewModel.finalRecipe.listOfStages[1].expirationDate}." +
                    "\n This recipe $fin" )
            Log.d("ING", "FINAL ingrs in S1 is $s2i" )

        })
        { Text(text = "Save")
        }
    }
}



@Composable
fun isFinished(viewModel: EditRecipeScreenViewModel) {
    var isChecked by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Напиток закончен")
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
        }
    viewModel.isRecipeFinished = isChecked
    }

