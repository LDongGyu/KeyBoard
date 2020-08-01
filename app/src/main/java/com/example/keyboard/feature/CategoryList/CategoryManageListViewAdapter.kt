package com.example.keyboard.feature.CategoryList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboard.R
import com.example.keyboard.data.GetCategory
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.KeyList.KeyListViewHolder

class CategoryManageListViewAdapter(var datas: List<GetCategory>): RecyclerView.Adapter<CategoryManageListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryManageListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.category_parent_item,parent,false)
        return CategoryManageListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CategoryManageListViewHolder, position: Int) {
        holder.bind(datas[position].category)
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }
    }

    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }


}