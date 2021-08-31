package com.avalanche.android.tincturediary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.avalanche.android.tincturediary.ui.screens.editRecipe.EditRecipeScreenViewModel
import com.avalanche.android.tincturediary.ui.components.Ingredient

@Composable
fun Stage(num: Int, viewModel: EditRecipeScreenViewModel, isRemovable: Boolean) : List<String> {

    val ingredients by viewModel.ingrCounter.observeAsState()
    var text by remember { mutableStateOf("") }
    var tempList = mutableListOf<String>()

    Column {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween  ) {
            Text("Фаза $num")
            Button(onClick = { viewModel.removeStage() },
                enabled = isRemovable) {
                Text("X")
            }
        }
        Divider(
            thickness = 0.5.dp,
            color = MaterialTheme.colors.secondaryVariant
        )
        Text("Ингредиенты")
        for (i in 0 until ingredients!! ) {
            Ingredient()
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                viewModel.addIngr()
            } ) {
                Text("Добавить ингредиент")
            }
        }
        Text("Описание")
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = text,
                onValueChange = {text = it},
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                placeholder = { Text("введите описание приготовления") }
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Дата окончания")
            Box(modifier = Modifier
                .size(20.dp)
                .background(MaterialTheme.colors.onError, RectangleShape))
        }
    }
    return tempList
}