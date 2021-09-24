package com.avalanche.android.tincturediary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avalanche.android.tincturediary.R
import com.avalanche.android.tincturediary.ui.screens.recipeScreen.RecipeScreenViewModel

@Composable
fun IngredientView(
    viewModel: RecipeScreenViewModel = viewModel(),
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
                placeholder = { Text(text = stringResource(id = R.string.title)) }
            )
            TextField(
                value = weightValue,
                onValueChange = {weightValue = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(0.25f)
                    .padding(4.dp),
                placeholder = { Text(text = stringResource(id = R.string.weight)) }
            )
        if (isRemovable) {
            Button(onClick = {
            viewModel.removeIngr(number)
            },
            modifier = Modifier.weight(0.15f)) {
            Text("X")
        }
        } else {
            Box(modifier = Modifier.weight(0.15f)) {

            }
        }
        viewModel.collectIngrsToFinal(number, index, titleValue, weightValue)
    }
}
