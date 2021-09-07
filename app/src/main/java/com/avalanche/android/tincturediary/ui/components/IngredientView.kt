package com.avalanche.android.tincturediary.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.ui.screens.editRecipe.EditRecipeScreenViewModel

@Composable
fun IngredientView(
    viewModel: EditRecipeScreenViewModel = viewModel(),
    title: String,
    weight: String,
    stageNum: Int,
    i: Int,
    isRemovable: Boolean) {

    var titleValue by remember { mutableStateOf(title) }
    var weightValue by remember { mutableStateOf(weight) }
    var number by remember { mutableStateOf(stageNum) }
    var index by remember { mutableStateOf(i) }

    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = titleValue,
                onValueChange = {titleValue = it},
                modifier = Modifier
                    .weight(0.6f)
                    .padding(4.dp),
                placeholder = { Text("название") }
            )
            TextField(
                value = weightValue,
                onValueChange = {weightValue = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(0.25f)
                    .padding(4.dp),
                placeholder = { Text("вес") }
            )
        if (isRemovable) {
            Button(onClick = {
            viewModel.removeIngr()
            },
            modifier = Modifier.weight(0.15f)) {
            Text("X")
        }
        } else {
            Box(modifier = Modifier.weight(0.15f)) {

            }
        }
    }
sendIngrs(viewModel, number, index, titleValue, weightValue)
}

fun sendIngrs(viewModel: EditRecipeScreenViewModel, stageNum: Int, index: Int, title: String, weight: String) {

}