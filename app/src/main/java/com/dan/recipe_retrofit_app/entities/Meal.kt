package com.dan.recipe_retrofit_app.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dan.recipe_retrofit_app.entities.converterTo.MealsConverter
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Meal")
class Meal(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "meals")
    @Expose
    @SerializedName("meals")
    @TypeConverters(MealsConverter::class)
    var mealsItem: List<Mealsdt>? = null
)