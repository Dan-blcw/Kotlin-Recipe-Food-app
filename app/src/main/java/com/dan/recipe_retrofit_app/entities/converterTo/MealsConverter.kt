package com.dan.recipe_retrofit_app.entities.converterTo

import androidx.room.TypeConverter
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealsConverter {
    @TypeConverter
    fun fromCategoryList(category: List<Mealsdt>):String?{
        if (category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<Mealsdt>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList ( categoryString: String):List<Mealsdt>?{
        if (categoryString == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<Mealsdt>(){

            }.type
            return  gson.fromJson(categoryString,type)
        }
    }
}