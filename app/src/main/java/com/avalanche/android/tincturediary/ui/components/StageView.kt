package com.avalanche.android.tincturediary.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.model.Stage
import com.avalanche.android.tincturediary.ui.screens.editRecipe.EditRecipeScreenViewModel
import com.avalanche.android.tincturediary.ui.components.IngredientView

@Composable
fun StageView(
    viewModel: EditRecipeScreenViewModel,
    num: Int,
    description: String,
    expDate: String,
    isRemovable: Boolean) {

    var description by remember { mutableStateOf(description) }
    var number by remember {mutableStateOf(num)}
    val stagesMap by viewModel.stagesMap.observeAsState()
    var counter by remember { mutableStateOf(0) }

        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text("Фаза $num")
            Text("added $counter ingredients")
                Button(
                    onClick = { viewModel.removeStage() },
                    enabled = isRemovable
                ) {
                    Text("X")
                }
            }
            Divider(
                thickness = 0.5.dp,
                color = MaterialTheme.colors.secondaryVariant
            )

            Text("Ингредиенты")
            Column(modifier = Modifier.fillMaxWidth()) {
                for (i in 0 until stagesMap!![number]!!.size) {
                    var isRemovable = if (i == stagesMap!![number]!!.size - 1) true else false
                    IngredientView(
                        viewModel,
                        stagesMap!![number]!![i].title,
                        stagesMap!![number]!![i].weight,
                        number,
                        i,
                        isRemovable
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    viewModel.addIngr(number)
                    counter++
                    Log.d(
                        "ADD",
                        "Recipe, stage 1, ingr list size ${viewModel.recipe.value!!.listOfStages[0].listOfIngredients.size} "
                    )
                    Log.d(
                        "ADD",
                        "Map, stage 1, ingr list size ${viewModel.stagesMap.value!![1]!!.size} "
                    )
                }) {
                    Text("Добавить ингредиент")
                }
            }
            Text("Описание")
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    placeholder = { Text("введите описание приготовления") }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Дата окончания")
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(MaterialTheme.colors.onError, RectangleShape)
                )
            }
        }

}