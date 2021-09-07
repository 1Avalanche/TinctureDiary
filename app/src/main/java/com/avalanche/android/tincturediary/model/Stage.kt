package com.avalanche.android.tincturediary.model

import java.util.*

class Stage(
    val number: Int,
    val listOfIngredients: List<Ingredient>,
    var description: String,
    var expirationDate: String
) {
}