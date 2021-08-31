package com.avalanche.android.tincturediary.model

import java.util.*

class Stage(
    var num: Int,
    var listOfIngredients: List<Ingredient>,
    var description: String,
    var expirationDate: Date
) {
}