package com.avalanche.android.tincturediary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.ui.screens.editRecipe.EditRecipeScreenViewModel

@Composable
fun IngredientView(viewModel: EditRecipeScreenViewModel = viewModel(), title: String, weight: String, i: Int) {

    val lastIndex by remember { mutableStateOf(viewModel.ingrList.value!!.lastIndex) }

    var titleValue by remember { mutableStateOf(title) }
    var weightValue by remember { mutableStateOf(weight) }
    val index by remember { mutableStateOf(i)}

    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        TextField(
            value = titleValue,
            onValueChange = {titleValue= it},
            modifier = Modifier
                .weight(0.6f)
                .padding(4.dp),
            placeholder = { Text("название") }
        )
        viewModel.ingrList.value!![index].title = titleValue
        TextField(
            value = weightValue,
            onValueChange = {weightValue = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.25f)
                .padding(4.dp),
            placeholder = { Text("вес") }
        )
        viewModel.ingrList.value!![index].weight = weightValue
        Button(onClick = {
            viewModel.removeIngr(1) },
            modifier = Modifier.weight(0.15f)) {
            Text("X")
        }
    }
}