package com.example.keyboard.feature.Category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.Category
import com.example.keyboard.data.GetStatus
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.activity_category_create.etcEditTxt
import kotlinx.android.synthetic.main.activity_category_create.titleEditTxt
import kotlinx.android.synthetic.main.activity_category_manage.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class CategoryManageActivity : AppCompatActivity() {

    private var isCanChange = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_manage)

        saveBtn.setOnClickListener(saveBtnClickListener)
        editBtn.setOnClickListener(editBtnClickListener)
        deleteBtn.setOnClickListener(deleteBtnClickListener)
    }

    private val saveBtnClickListener: View.OnClickListener = View.OnClickListener {
        var category = titleEditTxt.text.toString()
        var etc = etcEditTxt.text.toString()

        var data = Category(category,etc,UserInfo.id)
        CoroutineScope(Dispatchers.IO).launch {
            var statusCode = updateCategory(data)

            if(statusCode.equals("success")){
                finish()
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"카테고리 업데이트에 실패하였습니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun updateCategory(item: Category): String{
        var call: Call<GetStatus> = DBServiceImpl.service.categoryUpdate(item)
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }

    private val editBtnClickListener: View.OnClickListener = View.OnClickListener {
        if(!isCanChange){
            isCanChange = true
            editBtn.text = "수정 OFF"
        }
        else{
            isCanChange = false
            editBtn.text = "수정 ON"
        }
        toggleEditText(isCanChange)

    }

    private fun toggleEditText(flag: Boolean){
        titleEditTxt.isEnabled = flag
        etcEditTxt.isEnabled = flag
    }

    private val deleteBtnClickListener: View.OnClickListener = View.OnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            var category = titleEditTxt.text.toString()
            var etc = etcEditTxt.text.toString()

            var data = Category(category,etc,UserInfo.id)

            var deleteStatus = deleteCategory(data)

            if(deleteStatus.equals("success")){
                finish()
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"삭제에 실패하였습니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun deleteCategory(item: Category): String{
        val call: Call<GetStatus> = DBServiceImpl.service.categoryDelete(item)
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }
}
