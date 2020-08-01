package com.example.keyboard.feature.CategoryList

import android.graphics.drawable.Icon
import com.example.keyboard.feature.KeyList.KeyItem

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboard.R

class CategoryManageListViewHolder(item: View): RecyclerView.ViewHolder(item){

    val iconImg = item.findViewById(R.id.categoryImg) as de.hdodenhof.circleimageview.CircleImageView
    val categroyTxt = item.findViewById(R.id.categoryTxt) as TextView

    fun bind(data: String){
        iconImg.setImageIcon(Icon.createWithResource(itemView.context,R.drawable.logo))
        categroyTxt.text = data
    }
}