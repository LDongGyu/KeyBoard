package com.example.keyboard.KeyList

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboard.R

class KeyListViewAdapter(var datas: List<KeyItem>): RecyclerView.Adapter<KeyListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.key_item,parent,false)
        return KeyListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: KeyListViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}