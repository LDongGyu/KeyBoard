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
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.Category
import com.example.keyboard.data.GetCategory
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import java.security.Key

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CategoryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    lateinit var data: List<CategoryListItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        runBlocking {
            data = initData()
        }

        var view = inflater.inflate(R.layout.fragment_category, container, false)
        var categoryListView = view.findViewById(R.id.categoryListView) as ExpandableListView


        var categoryListAdapter = CategoryListViewAdapter(view.context,data)
        categoryListView.setAdapter(categoryListAdapter)
        categoryListView.setOnChildClickListener(childClickListener)

        return view
    }

    private suspend fun initData():List<CategoryListItem>{
        lateinit var temp:List<GetCategory>
        var data = mutableListOf<CategoryListItem>()

        val call: Call<List<GetCategory>> = DBServiceImpl.service.getCategory(UserInfo.id)
        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            temp = result.body()!!
        }
        job.join()
        /**
         * 자식 item 가져오기
         */
        var content1 = "네이버"
        var content2 = "카카오"
        var content3 = "배민"
        var content4 = "딜리버리히어로즈"
//        var childList  = listOf(content1,content2,content3,content4)

        for(category in temp){
            var childList = getChildItems(category.category)
            data.add(CategoryListItem(category.category,category.etc,childList))
        }
        return data
    }

    private val childClickListener : ExpandableListView.OnChildClickListener = ExpandableListView.OnChildClickListener { expandableListView, view, i, i2, l ->
        startActivity(Intent(context,
            CategoryManageActivity::class.java))
        return@OnChildClickListener false
    }

    private suspend fun getChildItems(category: String): List<KeyItem>{
        var body = Category(category,"",UserInfo.id)
        var call: Call<List<KeyItem>> = DBServiceImpl.service.childItemRead(body)
        lateinit var response:List<KeyItem>
        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            response = result.body()!!
        }
        job.join()
        return response
    }
}