package com.avalanche.android.tincturediary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avalanche.android.tincturediary.model.RecipePreparation

@Database(entities = [RecipePreparation::class], version = 1, exportSchema = false)
@TypeConverters(RecipeTypeConverters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao() : RecipeDao

//    companion object {
//        var INSTANCE: RecipeDatabase? = null
//
//        fun getAppDataBase(context: Context): RecipeDatabase? {
//            if (INSTANCE == null){
//                synchronized(RecipeDatabase::class){
//                    INSTANCE = Room.databaseBuilder(context.applicationContext, RecipeDatabase::class.java, "RecipiesDB").build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyDataBase(){
//            INSTANCE = null
//        }
//    }

}