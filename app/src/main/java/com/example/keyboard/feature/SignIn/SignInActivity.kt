package com.example.keyboard.feature.SignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetStatus
import com.example.keyboard.feature.MainActivity
import com.example.keyboard.feature.SignUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.security.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        SignUpBtn.setOnClickListener(signUpBtnClickListener)
        SignInBtn.setOnClickListener(signInBtnClickListener)
    }

    private val signUpBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(applicationContext,
            SignUpActivity::class.java))
        finish()
    }

    private val signInBtnClickListener: View.OnClickListener = View.OnClickListener {
        var id = idEditTxt.text.toString()
        var pw = pwEditTxt.text.toString()

        CoroutineScope(IO).launch {
            var loginStatus = login(id,pw)

            Log.d("loginLog","${id}, ${pw}, ${loginStatus}")
            if(loginStatus.equals("success")){
                var intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
                finish()
            }
            else{
                withContext(Main) {
                    Toast.makeText(applicationContext, "로그인 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun login(id: String, pw: String): String{
        val call: Call<GetStatus> = DBServiceImpl.service.signIn(id,pw.toMD5())
        var statusCode = "default"

        var job = CoroutineScope(IO).launch {
            var result = call.execute()
            statusCode = result.body()!!.status
        }
        job.join()

        return statusCode
    }

    private fun String.toMD5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray()).joinToString("") { "%02x".format(it) }
        return bytes
    }

}