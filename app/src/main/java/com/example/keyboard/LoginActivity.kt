package com.example.keyboard

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.keyboard.api.DBService
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetUserData
import com.google.gson.stream.JsonReader
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.NonCancellable.join
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SignInBtn.setOnClickListener {
            var id = idEditTxt.text.toString()
            var pw = pwEditTxt.text.toString()

            CoroutineScope(IO).launch {
                var loginStatus = login(id,pw)

                Log.d("loginLog","${id}, ${pw}, ${loginStatus}")
                if(loginStatus.equals("success")){
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    finish()
                }
                else{
                    withContext(Main) {
                        Toast.makeText(applicationContext, "로그인 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private suspend fun login(id: String, pw: String): String{
        val call: Call<GetUserData> = DBServiceImpl.service.signIn(id,pw)
        var statusCode = "default"

        var job = CoroutineScope(IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }

}
