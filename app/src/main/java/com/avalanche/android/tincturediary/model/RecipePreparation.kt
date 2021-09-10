package com.avalanche.android.tincturediary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RecipePreparation(
    @PrimaryKey(autoGenerate = true) val id: UUID = UUID.randomUUID(),
    val title: String,
    val listOfAlcoholBase: List<AlcoholBase>,
    val listOfStages: List<Stage>,
    val isFinished: Boolean,
) {
//    var finishedVolume: Float
}