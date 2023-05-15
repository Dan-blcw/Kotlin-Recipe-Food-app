package com.dan.recipe_retrofit_app.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val URL_INSTANCE = "https://www.themealdb.com/api/json/v1/1/"
        private var retrofit: Retrofit? = null
        val retrofitInstance: Retrofit?
            get() {
                if(retrofit == null){
                    retrofit = Retrofit.Builder()
                        .baseUrl(URL_INSTANCE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return  retrofit
            }

    }
}