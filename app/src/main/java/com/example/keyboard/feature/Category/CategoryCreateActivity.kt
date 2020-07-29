package com.example.keyboard.feature.Category

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.Category
import com.example.keyboard.data.GetCategory
import com.example.keyboard.data.GetStatus
import com.example.keyboard.feature.CategoryList.CategoryListItem
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.activity_category_create.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class CategoryCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_create)
        createBtn.setOnClickListener(categoryCreateBtnListener)
    }

    private val categoryCreateBtnListener: View.OnClickListener = View.OnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            var icon = Icon.createWithResource(applicationContext, R.drawable.logo)
            var title = titleEditTxt.text.toString()
            var etc = etcEditTxt.text.toString()

            var data = Category(title, etc, UserInfo.id)

            var signUpStatus = categoryCreate(data)

            if (signUpStatus.equals("success")) {
                finish()
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "카테고리가 이미 존재합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun categoryCreate(item: Category): String{
        val call: Call<GetStatus> = DBServiceImpl.service.categoryCreate(item)
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }

}
