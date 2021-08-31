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
fun Ingredient(viewModel: EditRecipeScreenViewModel = viewModel()) {

    var text by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        TextField(
            value = text,
            onValueChange = {text = it},
            modifier = Modifier
                .weight(0.6f)
                .padding(4.dp),
            placeholder = { Text("название") }
        )
        TextField(
            value = weight,
            onValueChange = {weight = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.25f)
                .padding(4.dp),
            placeholder = { Text("вес") }
        )
        Button(onClick = {
            viewModel.removeIngr() },
            modifier = Modifier.weight(0.15f)) {
            Text("X")
        }
    }
}