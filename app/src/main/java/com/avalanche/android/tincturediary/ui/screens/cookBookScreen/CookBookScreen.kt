package com.avalanche.android.tincturediary.ui.screens.cookBookScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.avalanche.android.tincturediary.R
import com.avalanche.android.tincturediary.database.RecipeDatabase
import com.avalanche.android.tincturediary.database.RecipeRepository
import com.avalanche.android.tincturediary.ui.components.RecipeCard
import com.avalanche.android.tincturediary.ui.components.RecipeCardCookbook

@ExperimentalFoundationApi
@Composable
fun CookBookScreen(navController: NavController, viewModel: CookBookViewModel = viewModel()) {

    val allNotFinishedRecepies by viewModel.allFinishedRecepies!!.observeAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box() {
            if (allNotFinishedRecepies == null) {
                Text(text = stringResource(id = R.string.recipes_loading))
            } else if (allNotFinishedRecepies!!.isEmpty()) {
                Text(text = stringResource(id = R.string.no_finished_recipes))
            } else {
                LazyColumn(
                    content = {
                        stickyHeader {
                            Text(text = stringResource(id = R.string.ready_recepies))
                        }
                        allNotFinishedRecepies?.forEach { recipe ->
                            item {RecipeCardCookbook(recipe = recipe, navController = navController)}
                        }
                    })
            }
        }
    }

}