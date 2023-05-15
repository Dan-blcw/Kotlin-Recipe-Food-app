package com.dan.recipe_retrofit_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dan.recipe_retrofit_app.R
import com.dan.recipe_retrofit_app.entities.dt.Mealsdt

class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {

    var listener: SubCategoryAdapter.OnItemClickListener? = null
    var context_ :Context? = null
    var arrSubCategory = ArrayList<Mealsdt>()
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    fun setData(arrData : List<Mealsdt>){
        arrSubCategory = arrData as ArrayList<Mealsdt>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        context_ = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lo_sub_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.itemView.apply {
            val image = findViewById<ImageView>(R.id.imgsubCategory)
            val disname = findViewById<TextView>(R.id.txtsubcategory)

            disname.text = arrSubCategory[position].strMeal
            Glide.with(context_!!).load(arrSubCategory[position].strMealThumb).into(image)
        }
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrSubCategory[position].idMeal)
        }
    }

    interface OnItemClickListener{
        fun onClicked(id:String)
    }
}