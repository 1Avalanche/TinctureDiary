package com.avalanche.android.tincturediary.ui.screens.readyRecipeScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avalanche.android.tincturediary.R
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.model.Stage
import java.util.*

@ExperimentalFoundationApi
@Composable
fun ReadyRecipeScreen(id: UUID, viewModel: ReadyRecipeScreenViewModel = viewModel()) {

    Log.d("ROUTE", "ReadyRecipeScreen loading")

    val uuid by remember { mutableStateOf(id) }
    viewModel.takeNewId(uuid)
    val recipe by viewModel.recipeLiveData.observeAsState()
    val listOFBases by viewModel.listOfAlcoholBase.observeAsState()
    val listOFStages by viewModel.listOfStages.observeAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn(content = {

                stickyHeader {
                    Text(
                        style = MaterialTheme.typography.h5,
                        text = recipe?.title ?: "Title"
                    )
                }

                item {
                    Text(text = stringResource(id = R.string.alcohol_bases))
                    listOFBases?.forEach { base ->
                        AlcoholBaseText(base = base)
                    }
                }

                item {
                    Text(text = stringResource(id = R.string.preparation))
                    listOFStages?.forEach { stage ->
                        StageText(stage)
                    }
                }

            })

    }

}

@Composable
fun AlcoholBaseText(base: AlcoholBase) {

    val base by remember { mutableStateOf(base) }

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(modifier = Modifier
            .weight(0.5f)
            .padding(4.dp),
            text = base.title)
        Text(modifier = Modifier
            .weight(0.3f)
            .padding(4.dp),
            text = base.volume + " ml")
        Text(modifier = Modifier
            .weight(0.2f)
            .padding(4.dp),
            text = base.strength + "%")
    }


}

@Composable
fun StageText(stage: Stage) {
    val stage by remember { mutableStateOf(stage)}
    val num by remember { mutableStateOf(stage.number)}

    Column() {
        Text(text = stringResource(id = R.string.stage) +"$num")
        Text(text = stringResource(id = R.string.ingredients))
        Column(horizontalAlignment = Alignment.Start) {
            stage.listOfIngredients. forEach { ingr ->
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(modifier = Modifier
                        .weight(0.7f)
                        .padding(4.dp),
                        text = ingr.title)
                    Text(modifier = Modifier
                        .weight(0.3f)
                        .padding(4.dp),
                        text = ingr.weight)
                }
            }
        }
        Text(text = stage.description)
    }
}