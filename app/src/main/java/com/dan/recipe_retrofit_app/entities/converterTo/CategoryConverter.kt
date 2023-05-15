package com.dan.recipe_retrofit_app.entities.converterTo

import androidx.room.TypeConverter
import com.dan.recipe_retrofit_app.entities.dt.Categorydt
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryConverter {
    @TypeConverter
    fun fromCategoryList(category: List<Categorydt>):String?{
        if (category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<Categorydt>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList ( categoryString: String):List<Categorydt>?{
        if (categoryString == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<Categorydt>(){

            }.type
            return  gson.fromJson(categoryString,type)
        }
    }
}