package com.dan.recipe_retrofit_app.entities

import com.dan.recipe_retrofit_app.entities.dt.MealsDetaildt
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealDetails (
    @Expose
    @SerializedName("meals")
    var mealsEntity: List<MealsDetaildt>
)