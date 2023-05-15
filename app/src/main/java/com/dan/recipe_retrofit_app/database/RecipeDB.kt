package com.dan.recipe_retrofit_app.database

import android.content.Context
import androidx.room.*
import com.dan.recipe_retrofit_app.entities.Category
import com.dan.recipe_retrofit_app.entities.Meal
import com.dan.recipe_retrofit_app.entities.RecipesKey
import com.dan.recipe_retrofit_app.entities.converterTo.CategoryConverter
import com.dan.recipe_retrofit_app.entities.converterTo.MealsConverter
import com.dan.recipe_retrofit_app.entities.dt.Categorydt
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt

/*
    Room chỉ lưu trữ các kiểu dữ liệu nguyên thủy nên giải pháp ở đây là converter Object
    -> dữ liệu nguyên thủy. Và mình cần sử dụng Gson để convert Object -> String
    Tạo ra 2 function để convert qua lại:
    Object -> String và String -> Object (như mình đã tọa là to... & from... ở trong
    package entities.converter)
    Và không thể quên anotation @TypeConverters
*/
@Database(
    entities = [
        RecipesKey::class,
        Categorydt::class,
        Category::class,
        Meal::class,
        Mealsdt::class],
    version = 1,
    exportSchema = false)
@TypeConverters(CategoryConverter:: class,MealsConverter::class)
abstract class RecipeDB: RoomDatabase(){
    companion object{
        val DB_NAME = "recipe.db"
        var recipesDatabase: RecipeDB? = null
        @Synchronized
        fun getDatabase(context: Context): RecipeDB{
            if(recipesDatabase == null){
                if (recipesDatabase == null){
                    recipesDatabase = Room.databaseBuilder(
                        context,
                        RecipeDB::class.java,
                        DB_NAME).build()
                }
            }
            return  recipesDatabase!!
        }

    }
    abstract fun recipeDao():RecipeDao
}