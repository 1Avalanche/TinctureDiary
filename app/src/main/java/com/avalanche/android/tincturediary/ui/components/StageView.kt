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
import com.avalanche.android.tincturediary.model.RecipePreparation
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

    val recipe by viewModel.recipe.observeAsState()
    var number by remember {mutableStateOf(num)}
    var description by remember { mutableStateOf(description) }

        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text("Фаза $num")
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
                for (i in 0 until recipe!!.listOfStages[number-1].listOfIngredients.size) {
                    var isRemovable = if (i == recipe!!.listOfStages[number-1].listOfIngredients.size - 1) true else false
                    IngredientView(
                        viewModel,
                        recipe!!.listOfStages[number-1].listOfIngredients[i].title,
                        recipe!!.listOfStages[number-1].listOfIngredients[i].weight,
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
                viewModel.finalRecipe.listOfStages[number-1].description = description
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