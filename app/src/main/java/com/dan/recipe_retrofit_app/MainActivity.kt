package com.dan.recipe_retrofit_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dan.recipe_retrofit_app.adapter.CategoryAdapter
import com.dan.recipe_retrofit_app.adapter.SubCategoryAdapter
import com.dan.recipe_retrofit_app.database.RecipeDB
import com.dan.recipe_retrofit_app.databinding.ActivityMainBinding
import com.dan.recipe_retrofit_app.entities.dt.Categorydt
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    var arrMainCategory = ArrayList<Categorydt>()
    var arrSubCategory = ArrayList<Mealsdt>()

    var categoryAdapter = CategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromDb()

        categoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)
    }

    private val onCLicked  = object : CategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            val intent = Intent(this@MainActivity,DetailDish::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        runBlocking {
            launch {
                this.let {
                    var cat = RecipeDB.getDatabase(this@MainActivity).recipeDao().getAllCategory()
                    arrMainCategory = cat as ArrayList<Categorydt>
                    arrMainCategory.reverse()

                    getMealDataFromDb(arrMainCategory[0].strcategory)
                    categoryAdapter.setData(arrMainCategory)
                    binding.rvCategory.layoutManager = LinearLayoutManager(this@MainActivity,
                        LinearLayoutManager.HORIZONTAL,false)
                    binding.rvCategory.adapter = categoryAdapter
                }


            }
        }

    }

    private fun getMealDataFromDb(categoryName:String){
        binding.txtCategory.text = "$categoryName"
        runBlocking {
            launch {
                this.let {
                    var cat = RecipeDB.getDatabase(this@MainActivity).recipeDao().getSpecificMealList(categoryName)
                    arrSubCategory = cat as ArrayList<Mealsdt>
                    subCategoryAdapter.setData(arrSubCategory)
                    binding.rvSubCategory.setHasFixedSize(true)
                    binding.rvSubCategory.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
                    binding.rvSubCategory.adapter = subCategoryAdapter
                }
            }
        }
    }
}