package com.example.keyboard.feature

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.keyboard.R
import com.example.keyboard.feature.Category.CategoryCreateActivity
import com.example.keyboard.feature.Item.ItemCreateActivity
import com.example.keyboard.feature.fragment.AllFragment
import com.example.keyboard.feature.fragment.CategoryFragment
import com.example.keyboard.feature.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentManager = supportFragmentManager
    private var allFragment = AllFragment()
    private var categoryFragment = CategoryFragment()
    private var settingFragment = SettingFragment()
    private var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragment, allFragment).commit()

        bottomNavigationView.setOnNavigationItemSelectedListener(clickListener)
        mainFab.setOnClickListener(mainFabClickListener)
        itemFab.setOnClickListener(itemFabClickListener)
        categoryFab.setOnClickListener(categoryFabClickListener)
    }

    private val clickListener: BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        var newTransaction = fragmentManager.beginTransaction()
        when(it.itemId){
            R.id.allItem -> newTransaction.replace(
                R.id.mainFragment, allFragment).commit()
            R.id.categoryItem -> newTransaction.replace(
                R.id.mainFragment, categoryFragment).commit()
            else -> newTransaction.replace(R.id.mainFragment, settingFragment).commit()
        }
        return@OnNavigationItemSelectedListener true
    }

    private val mainFabClickListener: View.OnClickListener = View.OnClickListener {
        if(!isOpen) { // fab이 눌리지 않았다면
            ObjectAnimator.ofFloat(itemFab, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(categoryFab, "translationY", -200f).apply { start() }
            isOpen = true
        }
        else{ // fab이 눌렸다면
            ObjectAnimator.ofFloat(itemFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(categoryFab, "translationY", 0f).apply { start() }
            isOpen = false
        }
    }

    private val itemFabClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, ItemCreateActivity::class.java))
    }

    private val categoryFabClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, CategoryCreateActivity::class.java))
    }
}

