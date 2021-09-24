package com.avalanche.android.tincturediary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
fun AlcoholBaseView(
    viewModel: RecipeScreenViewModel = viewModel(),
    title: String,
    vol: String,
    str: String,
    i: Int,
    isRemovable: Boolean) {

    val recipe = viewModel.recipe.value
    var name by remember { mutableStateOf(title ) }
    var volume by remember { mutableStateOf(vol) }
    var strenght by remember { mutableStateOf(str) }
    var index by remember { mutableStateOf(i) }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = name,
                onValueChange = {name = it},
                modifier = Modifier
                    .weight(0.5f)
                    .padding(4.dp),
                placeholder = { Text(text = stringResource(id = R.string.title)) }
            )
            TextField(
                value = volume,
                onValueChange = {volume = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(0.2f)
                    .padding(4.dp),
                placeholder = { Text(text = stringResource(id = R.string.placeh_10), style = MaterialTheme.typography.subtitle2) }
            )
            TextField(
                value = strenght,
                onValueChange = {strenght = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(0.2f)
                    .padding(4.dp),
                placeholder = { Text(text = stringResource(id = R.string.placeh_40), style = MaterialTheme.typography.subtitle2) }
            )
            if (isRemovable) {
                Button(onClick = {
                    viewModel.removeBase()
                },
                    modifier = Modifier.weight(0.1f)) {
                    Text("X")
                }
            } else {
                Box(modifier = Modifier.weight(0.1f)) {

                }
            }
            viewModel.collectBaseToFinal(index, name, volume, strenght)
        }
//    viewModel.recipe.value!!.listOfAlcoholBase[i].title = name
//    viewModel.recipe.value!!.listOfAlcoholBase[i].volume = volume
//    viewModel.recipe.value!!.listOfAlcoholBase[i].strength = strenght
}