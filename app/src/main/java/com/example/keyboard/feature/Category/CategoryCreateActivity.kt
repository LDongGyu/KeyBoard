package com.example.keyboard.feature.Category

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.keyboard.R
import kotlinx.android.synthetic.main.activity_category_create.*

class CategoryCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_create)
//        createBtn.setOnClickListener(categoryCreateBtnListener)
    }

//    private val categoryCreateBtnListener: View.OnClickListener = View.OnClickListener {
//        var icon = Icon.createWithResource(applicationContext,R.drawable.logo)
//    }

}
