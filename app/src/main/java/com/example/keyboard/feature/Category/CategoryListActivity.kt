package com.example.keyboard.feature.Category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetCategory
import com.example.keyboard.feature.CategoryList.CategoryManageListViewAdapter
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call

class CategoryListActivity : AppCompatActivity() {

    private lateinit var data: List<GetCategory>
    private lateinit var adapter: CategoryManageListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        runBlocking {
            initData()
        }

        adapter = CategoryManageListViewAdapter(data)
        adapter.setItemClickListener(object: CategoryManageListViewAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                var intent = Intent(applicationContext, CategoryManageActivity::class.java)
                intent.putExtra("category",data[position].category)
                intent.putExtra("etc",data[position].etc)
                startActivity(intent)
            }
        })
        categoryList.adapter = adapter
        categoryList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private suspend fun initData(){
        var job = CoroutineScope(Dispatchers.IO).launch {
            var call: Call<List<GetCategory>> = DBServiceImpl.service.getCategory(UserInfo.id)
            var response = call.execute()
            data = response.body()!!
        }
        job.join()
    }
}
