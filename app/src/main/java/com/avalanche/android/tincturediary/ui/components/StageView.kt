package com.avalanche.android.tincturediary.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.ui.screens.recipeScreen.RecipeScreenViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StageView(context: Context,
              viewModel: RecipeScreenViewModel,
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
            if (isRemovable || number > 1) {
                Button(
                    onClick = { viewModel.removeStage() },
                    enabled = isRemovable
                ) {
                    Text("X")
                }
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
            StageExpDate(context, viewModel, number)
        }
}

@Composable
fun StageExpDate(contex: Context, viewModel: RecipeScreenViewModel, stageNum: Int) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {
        var dateText by remember { mutableStateOf(SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())) }
        var descText by remember {mutableStateOf("Дата окончания:")}
        Text(descText)
        var cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            dateText = sdf.format(cal.time)

        }
        Button(onClick = {
            DatePickerDialog(contex, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()}) {
            Text(dateText)
        }
        var t: Date = Date(System.currentTimeMillis())
        viewModel.collectExpDate(stageNum, dateText)
    }
}