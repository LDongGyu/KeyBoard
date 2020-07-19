package com.example.keyboard.feature.KeyList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboard.R

class KeyListViewHolder(item: View): RecyclerView.ViewHolder(item){

    val iconImg = item.findViewById(R.id.iconImg) as de.hdodenhof.circleimageview.CircleImageView
    val titleTxt = item.findViewById(R.id.titleTxt) as TextView
    val categroyTxt = item.findViewById(R.id.categoryTxt) as TextView


    fun bind(data: KeyItem){
        iconImg.setImageIcon(data.icon)
        titleTxt.text = data.title
        categroyTxt.text = data.categroy
    }
}