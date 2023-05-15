package com.dan.recipe_retrofit_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dan.recipe_retrofit_app.R
import com.dan.recipe_retrofit_app.entities.dt.Categorydt
import kotlin.random.Random

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var context_: Context? = null
    var arrMainCategory = ArrayList<Categorydt>()
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    fun setData(arrData : List<Categorydt>){
        arrMainCategory = arrData as ArrayList<Categorydt>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        context_ = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lo_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.apply {
            val colorLayout = findViewById<CardView>(R.id.cart_layout)
            val image = findViewById<ImageView>(R.id.imgCategory)
            val disname = findViewById<TextView>(R.id.txtCategoryname)
            val index= random()
            colorLayout.setCardBackgroundColor(resources.getColor(index,null))
            colorLayout.setOnClickListener {
                listener!!.onClicked(arrMainCategory[position].strcategory)
            }
            disname.text = arrMainCategory[position].strcategory
            Glide.with(context_!!).load(arrMainCategory[position].strcategorythumb).into(image)
        }
    }

    interface OnItemClickListener{
        fun onClicked(categoryName:String)
    }
    fun random(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.do_1)
        list.add(R.color.do_2)
        list.add(R.color.do_3)
        list.add(R.color.do_4)
        list.add(R.color.do_5)
        list.add(R.color.do_6)
        list.add(R.color.do_7)
        list.add(R.color.do_8)
        list.add(R.color.do_9)
        list.add(R.color.do_10)
        list.add(R.color.do_11)
        list.add(R.color.do_12)
        list.add(R.color.do_13)
        list.add(R.color.do_14)
        list.add(R.color.do_15)
        list.add(R.color.do_16)
        list.add(R.color.do_17)
        list.add(R.color.do_18)
        list.add(R.color.do_19)
        list.add(R.color.do_20)
        list.add(R.color.do_21)
        list.add(R.color.do_22)
        list.add(R.color.do_23)
        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }
}