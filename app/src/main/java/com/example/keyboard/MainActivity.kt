package com.example.keyboard

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboard.KeyList.KeyItem
import com.example.keyboard.KeyList.KeyListViewAdapter
import com.example.keyboard.fragment.AllFragment
import com.example.keyboard.fragment.CategoryFragment
import com.example.keyboard.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    val clickListener: BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        var newTransaction = fragmentManager.beginTransaction()
        when(it.itemId){
            R.id.allItem -> newTransaction.replace(R.id.mainFragment, allFragment).commit()
            R.id.categoryItem -> newTransaction.replace(R.id.mainFragment, categoryFragment).commit()
            else -> newTransaction.replace(R.id.mainFragment, settingFragment).commit()
        }
        return@OnNavigationItemSelectedListener true
    }

    val mainFabClickListener: View.OnClickListener = View.OnClickListener {
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

    val itemFabClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, ItemCreateActivity::class.java))
    }

    val categoryFabClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, CategoryCreateActivity::class.java))
    }
}

