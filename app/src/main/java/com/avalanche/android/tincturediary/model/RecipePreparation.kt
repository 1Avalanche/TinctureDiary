package com.avalanche.android.tincturediary.model

data class RecipePreparation(
    val id: Int,
    val title: String,
    val listOfAlcoholBase: List<AlcoholBase>,
    val listOfStages: List<Stage>,
    val isFinished: Boolean,
) {
//    var finishedVolume: Float
}