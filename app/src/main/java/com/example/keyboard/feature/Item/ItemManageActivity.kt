package com.example.keyboard.feature.Item

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetStatus
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.activity_item_create.categorySpinner
import kotlinx.android.synthetic.main.activity_item_create.etcEditTxt
import kotlinx.android.synthetic.main.activity_item_create.idEditTxt
import kotlinx.android.synthetic.main.activity_item_create.pwEditTxt
import kotlinx.android.synthetic.main.activity_item_create.titleEditTxt
import kotlinx.android.synthetic.main.activity_item_create.urlEditTxt
import kotlinx.android.synthetic.main.activity_item_manage.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class ItemManageActivity : AppCompatActivity() {

    private var isCanChange = true
    private var beforeTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_manage)
        editBtn.setOnClickListener(editBtnClickListener)
        categorySpinner.adapter = getCategory()

        var category = intent.getStringExtra("category")

        beforeTitle = intent.getStringExtra("title")
        categorySpinner.setSelection(0)
        titleEditTxt.setText(intent.getStringExtra("title"))
        idEditTxt.setText(intent.getStringExtra("id"))
        pwEditTxt.setText(intent.getStringExtra("pw"))
        urlEditTxt.setText(intent.getStringExtra("url"))
        etcEditTxt.setText(intent.getStringExtra("etc"))

        saveBtn.setOnClickListener(saveBtnClickListener)
        deleteBtn.setOnClickListener(deleteBtnClickListener)
    }

    private val saveBtnClickListener: View.OnClickListener = View.OnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            var icon = Icon.createWithResource(applicationContext,R.drawable.logo)
            var title = titleEditTxt.text.toString()
            var category = categorySpinner.selectedItem.toString()
            var id = idEditTxt.text.toString()
            var pw = pwEditTxt.text.toString()
            var url = urlEditTxt.text.toString()
            var etc = etcEditTxt.text.toString()

            var data = KeyItem(icon,title,category,id,pw,url,etc, UserInfo.id,beforeTitle)
            var deleteStatus = itemUpdate(data)
            if(deleteStatus.equals("success")){
                finish()
            }
            else{
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "저장에 실패하였습니다..", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun itemUpdate(item: KeyItem): String{
        val call: Call<GetStatus> = DBServiceImpl.service.itemUpdate(item)
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }

    private val deleteBtnClickListener: View.OnClickListener = View.OnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            var icon = Icon.createWithResource(applicationContext,R.drawable.logo)
            var title = titleEditTxt.text.toString()
            var category = categorySpinner.selectedItem.toString()
            var id = idEditTxt.text.toString()
            var pw = pwEditTxt.text.toString()
            var url = urlEditTxt.text.toString()
            var etc = etcEditTxt.text.toString()

            var data = KeyItem(icon,title,category,id,pw,url,etc, UserInfo.id)
            Log.d("delete",data.toString())
            var deleteStatus = itemDelete(data)
            if(deleteStatus.equals("success")){
                finish()
            }
            else{
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "삭제에 실패하였습니다..", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun itemDelete(item: KeyItem): String{
        val call: Call<GetStatus> = DBServiceImpl.service.itemDelete(item)
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
        idEditTxt.isEnabled = flag
        pwEditTxt.isEnabled = flag
        urlEditTxt.isEnabled = flag
        etcEditTxt.isEnabled = flag
    }

    private fun getCategory(): ArrayAdapter<String> {
        var spinnerAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item)
        spinnerAdapter.add("네이버 가자")
        spinnerAdapter.add("카카오 가자")

        return spinnerAdapter
    }

}