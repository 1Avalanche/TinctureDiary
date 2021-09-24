package com.avalanche.android.tincturediary.ui.components

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.avalanche.android.tincturediary.model.Id
import com.avalanche.android.tincturediary.model.RecipePreparation
import com.avalanche.android.tincturediary.navigation.navigate
import kotlinx.android.parcel.Parcelize
import java.util.*


@Composable
fun RecipeCardCookbook(recipe: RecipePreparation, navController: NavController) {

    val id by remember { mutableStateOf(Id(recipe.id)) }
    val title by remember { mutableStateOf(recipe.title) }


    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        .clickable(
            true,
            null,
            null,
            onClick = { navigate(navController, id)
                Log.d("ROUTE", "card clicked")}),
        elevation = 8.dp) {
        Column(modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start) {
            Row() {
                Text(modifier = Modifier.weight(0.9f),
                    style = MaterialTheme.typography.h5,
                    text = title)

            }
            Row() {
                Text(modifier = Modifier.weight(0.1f),
                    text ="Крепость: 42%") //TODO
            }
        }
    }
}

fun navigate(navController: NavController, id: Id) {
    navController.navigate("readyRecipe", bundleOf("TAKE_ID" to id))
}