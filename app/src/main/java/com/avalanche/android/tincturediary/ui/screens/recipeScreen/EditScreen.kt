package com.avalanche.android.tincturediary.ui.screens.recipeScreen

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
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
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.ui.components.AlcoholBaseView
import com.avalanche.android.tincturediary.ui.components.IsFinished
import com.avalanche.android.tincturediary.ui.components.StageView
import java.util.*

//Screen for edit recipe witch exist
@ExperimentalFoundationApi
@Composable
fun EditScreen(context: Context, id: UUID, viewModel: RecipeScreenViewModel = viewModel()) {

    val uuid by remember { mutableStateOf(id) }
    viewModel.takeNewId(uuid)
    val recipeFromDB by viewModel.recipeLiveData.observeAsState()
    var counter by remember { mutableStateOf(true) }

    if (counter && (recipeFromDB != null)) {
        viewModel.refreshVM(recipeFromDB!!)
        counter = false //fun can call one time
    }

    val recipeRefreshed by viewModel.recipeRefreshed.observeAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        if (recipeRefreshed!!) {

            Log.d("ROUTE", "Column call'd")

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            )
            {
                stickyHeader {
                    EditToolbar(viewModel)
                }
                item {
                    Title(viewModel)
                }
                item {
                    AlcoholBase(viewModel)
                }
                item {
                    Stages(context, viewModel)
                }
                item { IsFinished(viewModel) }
            }
        }
    }

}

@Composable
fun EditToolbar(viewModel: RecipeScreenViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {/*TODO*/ }) {
            Text("Prev")
        }
        Text(text = stringResource(id = R.string.edit_recipe))
        Button(onClick = {
            viewModel.insertRp()
        })
        { Text(text = "Save")
        }
    }
}

@Composable
fun Title(viewModel: RecipeScreenViewModel) {

    val recipe by viewModel.recipe.observeAsState()
    var text by remember { mutableStateOf(recipe!!.title) }

    OutlinedTextField(
        value = text,
        readOnly = true,
        onValueChange = { text = it
        },
        label = { Text(text = stringResource(id = R.string.title)) } )
    viewModel.finalRecipe.title = text
}

@Composable
fun AlcoholBase(viewModel: RecipeScreenViewModel) {

    val recipe by viewModel.recipe.observeAsState()

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        Text(text = stringResource(id = R.string.alcohol_base))
    }

    for (i in 0 until recipe!!.listOfAlcoholBase.size) {
        var isRemovable = if (i == recipe!!.listOfAlcoholBase.size - 1) true else false
        AlcoholBaseView(
            viewModel,
            recipe!!.listOfAlcoholBase[i]!!.title,
            recipe!!.listOfAlcoholBase[i]!!.volume,
            recipe!!.listOfAlcoholBase[i]!!.strength,
            i,
            isRemovable
        )
    }

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Button(onClick = {
            viewModel.addBase()
        } ) {
            Text(text = stringResource(id = R.string.add_base))
        }
    }
}

@Composable
fun Stages(context: Context, viewModel: RecipeScreenViewModel) {

    val recipe by viewModel.recipe.observeAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        for (i in 0 until recipe!!.listOfStages.size) {
            var isRemovable = if (i == recipe!!.listOfStages.size - 1) true else false
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

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Button(onClick = {
            viewModel.addStage()
        } ) {
            Text(text = stringResource(id = R.string.add_stage))
        }
    }
}
