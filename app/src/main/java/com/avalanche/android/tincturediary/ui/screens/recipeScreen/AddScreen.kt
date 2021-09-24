package com.avalanche.android.tincturediary.ui.screens.recipeScreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avalanche.android.tincturediary.R
import com.avalanche.android.tincturediary.ui.components.AlcoholBaseView
import com.avalanche.android.tincturediary.ui.components.IsFinished
import com.avalanche.android.tincturediary.ui.components.StageView

//Screen to add new recipe
@ExperimentalFoundationApi
@Composable
fun AddScreen(context: Context, viewModel: RecipeScreenViewModel = viewModel()) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        val recipe by viewModel.recipe.observeAsState()
        var text by remember { mutableStateOf(recipe!!.title) }
        var finalRecipe by remember { mutableStateOf(viewModel.finalRecipe) }

        LazyColumn(
            modifier = Modifier.fillMaxSize())
        {
            stickyHeader {
                AddToolbar(viewModel)
            }
            item {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it
                    },
                    label = { Text(text = stringResource(id = R.string.title)) } )
                viewModel.finalRecipe.title = text
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Text(text = stringResource(id = R.string.alcohol_base))
                }
            }
            item{
                for (i in 0 until recipe!!.listOfAlcoholBase.size) {
                    var isRemovable = if (i == recipe!!.listOfAlcoholBase.size - 1) true else false
                    AlcoholBaseView(
                        viewModel,
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
                        viewModel.addBase()
                    } ) {
                        Text(text = stringResource(id = R.string.add_base))
                    }
                }
            }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    for (i in 0 until recipe!!.listOfStages.size) {
                        var isRemovable = if (i == recipe!!.listOfStages.size - 1) false else true
                        StageView(
                            context,
                            viewModel,
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
                        viewModel.addStage()
                    } ) {
                        Text(text = stringResource(id = R.string.add_stage))
                    }
                }
            }
            item {IsFinished(viewModel)}
        }
    }
}

@Composable
fun AddToolbar(viewModel: RecipeScreenViewModel) {
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
            viewModel.insertRp()
        })
        { Text(text = "Save")
        }
    }
}

