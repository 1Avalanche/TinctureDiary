package com.avalanche.android.tincturediary.ui.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.avalanche.android.tincturediary.ui.screens.editRecipe.EditRecipeScreenViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickerView(contex: Context) {

    Row(modifier = Modifier.fillMaxWidth()
        .wrapContentHeight()) {

        var dateText by remember { mutableStateOf(SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())) }
        var descText by remember {mutableStateOf("Дата окончания")}
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
    }
}