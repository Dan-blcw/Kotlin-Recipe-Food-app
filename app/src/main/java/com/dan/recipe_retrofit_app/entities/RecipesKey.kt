package com.dan.recipe_retrofit_app.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Recipes")
data class RecipesKey(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "dishName")
    var dishName:String
):Serializable