package com.example.keyboard.feature.Item

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetCategory
import com.example.keyboard.data.GetStatus
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.MainActivity
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.activity_item_create.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class ItemCreateActivity : AppCompatActivity() {

    private lateinit var categoryList: MutableList<GetCategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_create)
        createBtn.setOnClickListener(itemCreateBtnClickListener)
    }

    private val itemCreateBtnClickListener: View.OnClickListener = View.OnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            var icon = Icon.createWithResource(applicationContext,R.drawable.logo)
            var title = titleEditTxt.text.toString()
            var category = categorySpinner.selectedItem.toString()
            var id = idEditTxt.text.toString()
            var pw = pwEditTxt.text.toString()
            var url = urlEditTxt.text.toString()
            var etc = etcEditTxt.text.toString()

            var data = KeyItem(icon,title,category,id,pw,url,etc)

            var signUpStatus = itemCreate(data)

            if(signUpStatus.equals("success")){
                finish()
            }
            else{
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "회원정보가 이미 존재합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun itemCreate(item: KeyItem): String{
        val call: Call<GetStatus> = DBServiceImpl.service.itemCreate(item,UserInfo.id)
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }
}