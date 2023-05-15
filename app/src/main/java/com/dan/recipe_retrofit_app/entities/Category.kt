package com.dan.recipe_retrofit_app.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dan.recipe_retrofit_app.entities.converterTo.CategoryConverter
import com.dan.recipe_retrofit_app.entities.dt.Categorydt
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryConverter::class)
    var categorieitems: List<Categorydt>? = null
)