package com.example.keyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_item_create.*

class ItemCreateActivity : AppCompatActivity() {

    private var isCanChange = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_create)

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
        idEditTxt.isEnabled = flag
        pwEditTxt.isEnabled = flag
        urlEditTxt.isEnabled = flag
        etcEditTxt.isEnabled = flag
    }
}
