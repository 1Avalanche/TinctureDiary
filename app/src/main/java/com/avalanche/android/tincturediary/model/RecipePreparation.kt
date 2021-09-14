package com.avalanche.android.tincturediary.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*


@Entity
data class RecipePreparation(
    @PrimaryKey val id: UUID,
    val title: String,
    val listOfAlcoholBase: List<AlcoholBase>,
    val listOfStages: List<Stage>,
    val isFinished: Boolean,
)  {
//    var finishedVolume: Float
}