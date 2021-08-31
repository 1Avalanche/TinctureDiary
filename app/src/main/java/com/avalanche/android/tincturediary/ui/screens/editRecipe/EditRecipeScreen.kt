package com.avalanche.android.tincturediary.ui.screens.editRecipe

import android.util.Log
import android.widget.CheckBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import com.avalanche.android.tincturediary.ui.components.TitleField
import com.avalanche.android.tincturediary.ui.components.Stage
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.RectangleShape
import com.avalanche.android.tincturediary.ui.components.AlcoholBase

@ExperimentalFoundationApi
@Composable
fun EditRecipeScreen(editRecipeScreenViewModel: EditRecipeScreenViewModel = viewModel()) {

    val bases by editRecipeScreenViewModel.baseCounter.observeAsState()
    val stages by editRecipeScreenViewModel.stageCounter.observeAsState()


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            stickyHeader {
                Toolbar(editRecipeScreenViewModel)
            }
            item {
                TitleField(text = "Название")
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Text("Спиртовая основа")
                }
            }
            item{
                for (i in 0 until bases!!) {
                    AlcoholBase("")
                }
            }
            item{
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        editRecipeScreenViewModel.addBase()
                      } ) {
                        Text("Добавить основу")
                    }
                }
            }
            item {
                for (i in 0 until stages!!) {
                    if (i == 0 ) {
                        Stage(i+1, editRecipeScreenViewModel, false)
                    } else {
                        Stage(i + 1, editRecipeScreenViewModel, true)
                    }
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        editRecipeScreenViewModel.addStage()
                    } ) {
                        Text("Добавить фазу")
                    }
                }
            }
            item {isFinished()}
    } )
}

@Composable
fun Toolbar(editRecipeScreenViewModel: EditRecipeScreenViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {/*TODO*/ }) {
            Text("Prev")
        }
        Button(onClick = {/*TODO*/}) {
            Text(text = "Save")
        }
    }
}



@Composable
fun isFinished() {
    var onCheckedChange: ((Boolean) -> Unit)? = null
    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Напиток закончен")
        Checkbox(
            checked = false,
            onCheckedChange = onCheckedChange
            )
    }
}
