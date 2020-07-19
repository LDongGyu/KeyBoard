package com.example.keyboard.feature.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.example.keyboard.feature.CategoryList.CategoryListItem
import com.example.keyboard.feature.CategoryList.CategoryListViewAdapter
import com.example.keyboard.feature.Category.CategoryManageActivity

import com.example.keyboard.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CategoryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_category, container, false)
        var categoryListView = view.findViewById(R.id.categoryListView) as ExpandableListView

        var data = initData()
        var categoryListAdapter = CategoryListViewAdapter(view.context,data)
        categoryListView.setAdapter(categoryListAdapter)
        categoryListView.setOnChildClickListener(childClickListener)

        return view
    }

    private fun initData():List<CategoryListItem>{
        var title1 = "홈페이지"
        var title2 = "PC방"
        var title3 = "회사"

        var content1 = "네이버"
        var content2 = "카카오"
        var content3 = "배민"
        var content4 = "딜리버리히어로즈"
        var childList = listOf(content1,content2,content3,content4)

        var temp1 = CategoryListItem(title1,childList)
        var temp2 = CategoryListItem(title2,childList)
        var temp3 = CategoryListItem(title3,childList)

        var data = listOf(temp1,temp2,temp3)
        return data
    }

    private val childClickListener : ExpandableListView.OnChildClickListener = ExpandableListView.OnChildClickListener { expandableListView, view, i, i2, l ->
        startActivity(Intent(context,
            CategoryManageActivity::class.java))
        return@OnChildClickListener false
    }
}
