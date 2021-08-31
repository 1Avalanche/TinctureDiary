package com.avalanche.android.tincturediary.model

data class RecipePreparation(
    var title: String,
    var listOfAlcoholBase: List<AlcoholBase>,
    var listOfStages: List<Stage>,
    var isFinished: Boolean,
    var finishedVolume: Float
) {
}