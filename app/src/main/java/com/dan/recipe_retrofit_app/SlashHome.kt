package com.dan.recipe_retrofit_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dan.recipe_retrofit_app.database.RecipeDB
import com.dan.recipe_retrofit_app.databinding.ActivitySlashHomeBinding
import com.dan.recipe_retrofit_app.entities.Category
import com.dan.recipe_retrofit_app.entities.Meal
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt
import com.dan.recipe_retrofit_app.retrofit.GetDataFromURL
import com.dan.recipe_retrofit_app.retrofit.RetrofitInstance
import kotlinx.coroutines.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivitySlashHomeBinding
class SlashHome : AppCompatActivity() ,EasyPermissions.RationaleCallbacks,
EasyPermissions.PermissionCallbacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlashHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        performStorage()

        binding.btnSlashHome.setOnClickListener {
            val intentToMain = Intent(this,MainActivity::class.java)
            startActivity(intentToMain)
            finish()
        }
    }
    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

    }

    private fun performStorage() {
        if(hasReadStoragePermission()){
            clearUtil()
            takeCategories()
        }else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage,",
                123,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun takeCategories() {
        val servive = RetrofitInstance.retrofitInstance!!.create(GetDataFromURL::class.java)
        val call = servive.getAllCategory()
        call.enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                for(item in response.body()!!.categorieitems!!){
                    takeMeal(item.strcategory)
                }
                insertDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@SlashHome, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun insertDataIntoRoomDb(body: Category?) {
        runBlocking {
            launch {
                this.let {

                    for (arr in body!!.categorieitems!!) {
                        RecipeDB.getDatabase(this@SlashHome)
                            .recipeDao().insertCategory(arr)
                    }
                }
            }
        }
    }

    private fun takeMeal(categoryName: String) {
        val servive = RetrofitInstance.retrofitInstance!!.create(GetDataFromURL::class.java)
        val call = servive.getAllMeal(categoryName)
        call.enqueue(object: Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                insertMealDataIntoRoomDb(categoryName, response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                Toast.makeText(this@SlashHome, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun insertMealDataIntoRoomDb(categoryName: String, body: Meal?) {
        runBlocking {
            launch {
                this.let {
                    for (arr in body!!.mealsItem!!) {
                        val mealItemModel = Mealsdt(
                            arr.id,
                            arr.idMeal,
                            categoryName,
                            arr.strMeal,
                            arr.strMealThumb
                        )
                        RecipeDB.getDatabase(this@SlashHome)
                            .recipeDao().insertMeal(mealItemModel)
                        Log.d("mealData", arr.toString())
                    }
//                    btnGetStarted.visibility = View.VISIBLE
                }
            }
        }
    }

    fun clearUtil(){
        runBlocking {
            launch {
                this.let {
                    RecipeDB.getDatabase(this@SlashHome).recipeDao().clearDb()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleAccepted(requestCode: Int) {}
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
    override fun onRationaleDenied(requestCode: Int) {}
}