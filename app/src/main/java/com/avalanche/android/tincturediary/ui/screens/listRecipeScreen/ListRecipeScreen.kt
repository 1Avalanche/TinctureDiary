package com.avalanche.android.tincturediary.ui.screens.listRecipeScreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.avalanche.android.tincturediary.R
import com.avalanche.android.tincturediary.ui.components.RecipeCard

//Screen for show not finished recepies (in one screen several cards)
//Click in card must show EditScreen
@ExperimentalFoundationApi
@Composable
fun ListRecipeScreen(context: Context, navController: NavController, viewModel: ListRecipeScreenViewModel = viewModel()) {

    val notFinishedRecipies by viewModel.allNotFinishedRecipies.observeAsState()
    Log.d("LOAD", "ListScreen loaded")
    Surface(modifier = Modifier.fillMaxSize()) {
            Box() {
                if (notFinishedRecipies == null || notFinishedRecipies!!.isEmpty()) {
                    Text("Готовящихся напитков нет")
                } else {
                    LazyColumn(
                        content = {
                            notFinishedRecipies?.forEach { recipe ->
                                item { RecipeCard(recipe = recipe, context, navController) }
                            }

                        })
//                FloatingActionButton(modifier = Modifier
//                    .align(BottomEnd)
//                    .padding(8.dp),
//                    onClick = { /*TODO*/ }) {
//                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add new recipe" )
//                }
                }
            }
        }
}

