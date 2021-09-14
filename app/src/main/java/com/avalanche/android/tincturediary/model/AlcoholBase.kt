package com.avalanche.android.tincturediary.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

data class AlcoholBase(
    var title: String,
    var volume: String,
    var strength: String,
) {

//    var volumeFloat = volume.replace(',', '.', true).toFloat()
//    var strengthFloat = strength.replace(',', '.', true).toFloat()

}