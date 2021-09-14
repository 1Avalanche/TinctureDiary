package com.avalanche.android.tincturediary.database

import androidx.room.TypeConverter
import com.avalanche.android.tincturediary.model.AlcoholBase
import com.avalanche.android.tincturediary.model.Ingredient
import com.avalanche.android.tincturediary.model.Stage
import com.google.gson.Gson
import java.util.*


class RecipeTypeConverters {

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    //Converters to save complex values to json
    @TypeConverter
    fun alcoholBaseListToJsonString(value: List<AlcoholBase>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToAlcoholBaseList(value: String) = Gson().fromJson(value, Array<AlcoholBase>::class.java).toList()

    @TypeConverter
    fun stageListToJsonString(value: List<Stage>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToStageList(value: String) = Gson().fromJson(value, Array<Stage>::class.java).toList()


}