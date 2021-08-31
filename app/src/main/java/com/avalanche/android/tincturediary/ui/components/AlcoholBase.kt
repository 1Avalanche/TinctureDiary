package com.avalanche.android.tincturediary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AlcoholBase(_text: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var name by remember { mutableStateOf(_text) }
            var vol by remember { mutableStateOf(_text) }
            var str by remember { mutableStateOf(_text) }
            TextField(
                value = name,
                onValueChange = {name = it},
                modifier = Modifier
                    .weight(0.5f)
                    .padding(4.dp),
                placeholder = { Text("название") }
            )
            TextField(
                value = vol,
                onValueChange = {vol = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(0.25f)
                    .padding(4.dp),
                placeholder = { Text("ml") }
            )
            TextField(
                value = str,
                onValueChange = {str = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(0.25f)
                    .padding(4.dp),
                placeholder = { Text("%") }
            )
        }
    }
}