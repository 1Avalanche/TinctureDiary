package com.avalanche.android.tincturediary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.avalanche.android.tincturediary.R
import com.avalanche.android.tincturediary.ui.screens.recipeScreen.RecipeScreenViewModel

@Composable
fun IsFinished(viewModel: RecipeScreenViewModel) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.finished))
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
    viewModel.isRecipeFinished = isChecked
}