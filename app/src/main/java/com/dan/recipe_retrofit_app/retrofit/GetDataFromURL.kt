package com.dan.recipe_retrofit_app.retrofit

import com.dan.recipe_retrofit_app.entities.Category
import com.dan.recipe_retrofit_app.entities.Meal
import com.dan.recipe_retrofit_app.entities.MealDetails
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataFromURL {
    @GET("categories.php")
    fun getAllCategory(): Call<Category>

    @GET("filter.php")
    fun getAllMeal(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealDetails>
}