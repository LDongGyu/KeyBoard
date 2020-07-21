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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_create)
        Toast.makeText(applicationContext,"${UserInfo.id}",Toast.LENGTH_SHORT).show()
        createBtn.setOnClickListener(itemCreateBtnClickListener)
    }

    private val itemCreateBtnClickListener: View.OnClickListener = View.OnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            var icon = Icon.createWithResource(applicationContext,R.drawable.logo)
            var data = KeyItem(icon,"네이버","홈페이지","id","pw","url","etc")

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
        val call: Call<GetStatus> = DBServiceImpl.service.itemCreate(item)
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }
}