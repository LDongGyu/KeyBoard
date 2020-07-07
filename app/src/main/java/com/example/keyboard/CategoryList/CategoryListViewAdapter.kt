package com.example.keyboard.CategoryList

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.keyboard.R

class CategoryListViewAdapter(var context: Context, var datas: List<CategoryListItem>): BaseExpandableListAdapter() {

    override fun getGroup(p0: Int): Any {
        return datas.get(p0)
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(p0: Int, p1: Boolean,p2: View?, p3: ViewGroup?): View {
        var inflater =context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view =inflater.inflate(R.layout.category_parent_item,p3,false)

        var categoryTxt = view.findViewById(R.id.categoryTxt) as TextView
        var categoryImg = view.findViewById(R.id.categoryImg) as de.hdodenhof.circleimageview.CircleImageView

        categoryTxt.text = datas.get(p0).category
        categoryImg.setImageResource(R.drawable.logo)

        return view
    }

    override fun getChildrenCount(p0: Int): Int {
        return datas.get(p0).child.size
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return datas.get(p0).child.get(p1)
    }

    override fun getGroupId(p0: Int): Long {
        var groupId = p0.toLong()
        return groupId
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.category_child_item,p4,false)
        var itemTxt = view.findViewById(R.id.itemTxt) as TextView

        itemTxt.text = datas.get(p0).child.get(p1)

        return view
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        var childId = p1.toLong()
        return childId
    }

    override fun getGroupCount(): Int {
        return datas.size
    }

}