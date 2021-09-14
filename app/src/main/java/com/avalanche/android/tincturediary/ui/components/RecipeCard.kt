package com.avalanche.android.tincturediary.ui.components

import android.content.Context
import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.avalanche.android.tincturediary.model.RecipePreparation
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.avalanche.android.tincturediary.navigation.navigate
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Id(var value: UUID) : Parcelable

//Small card view for ListScreen
@Composable
fun RecipeCard(recipe: RecipePreparation, context: Context, navController: NavController) {

    val id by remember { mutableStateOf(Id(recipe.id))}
    val title by remember {mutableStateOf(recipe.title)}
    val activeStage by remember { mutableStateOf(recipe.listOfStages.size)}
    val expDate by remember { mutableStateOf(recipe.listOfStages.last().expirationDate)}
    
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        .clickable(
            true,
            null,
            null,
            onClick = { navController.navigate("edit", bundleOf("TAKE_ID" to id))  //send id by NavHost
                        Log.d("LOAD", "Card clicked and send ${id.toString()}")}),
        elevation = 8.dp) {
        Column(modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start) {
            Row() {
                Text(style = MaterialTheme.typography.h5,
                    text = title)
            }
            Row() {
                Text("Активная стадия: $activeStage")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Дата окончания стадии")
                Text(expDate)
            }
        }
    }
}