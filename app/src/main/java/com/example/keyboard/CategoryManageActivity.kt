package com.example.keyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_category_create.*
import kotlinx.android.synthetic.main.activity_category_create.etcEditTxt
import kotlinx.android.synthetic.main.activity_category_create.titleEditTxt
import kotlinx.android.synthetic.main.activity_category_manage.*

class CategoryManageActivity : AppCompatActivity() {

    private var isCanChange = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_manage)

        editBtn.setOnClickListener(editBtnClickListener)
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
}
