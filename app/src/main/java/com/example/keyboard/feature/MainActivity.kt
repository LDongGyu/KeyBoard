package com.example.keyboard.feature

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetID
import com.example.keyboard.feature.Category.CategoryCreateActivity
import com.example.keyboard.feature.Item.ItemCreateActivity
import com.example.keyboard.feature.Singleton.UserInfo
import com.example.keyboard.feature.fragment.AllFragment
import com.example.keyboard.feature.fragment.CategoryFragment
import com.example.keyboard.feature.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Call

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
        runBlocking {
            UserInfo.id = getUserID()
            Log.d("login","${UserInfo.id}")
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(clickListener)
        mainFab.setOnClickListener(mainFabClickListener)
        itemFab.setOnClickListener(itemFabClickListener)
        categoryFab.setOnClickListener(categoryFabClickListener)
    }

    private suspend fun getUserID(): Int{
        var intentId = intent.getStringExtra("id") ?: "ID"
        val call: Call<GetID> = DBServiceImpl.service.getUserID(intentId)
        var id = 0
        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            id = result.body()!!.id
            Log.d("login","get ${id}")
        }
        job.join()
        return id
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

