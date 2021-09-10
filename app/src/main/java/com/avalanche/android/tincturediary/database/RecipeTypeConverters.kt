package com.avalanche.android.tincturediary.database

import androidx.room.TypeConverter
import com.avalanche.android.tincturediary.model.AlcoholBase
import java.util.*

private const val SEPARATOR = ","

class RecipeTypeConverters {

    //TODO

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }
    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
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

//    @TypeConverter
//    fun toDoubleList(flatStringList: String) : List<List<String>> {
//
//    }
//
//    @TypeConverter
//    fun alcoholBaseToString(basesList: List<AlcoholBase>?) : String? {
//        return AlcoholBase?.map
//    }

}