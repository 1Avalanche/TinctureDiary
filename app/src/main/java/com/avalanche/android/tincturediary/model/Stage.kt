package com.avalanche.android.tincturediary.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

//нужен ли фазе номер???
data class Stage(
    val number: Int,
    @Embedded val listOfIngredients: List<Ingredient>,
    var description: String,
    var expirationDate: String,
) {
}