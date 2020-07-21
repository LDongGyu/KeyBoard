package com.example.keyboard.feature.SignUp

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
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.idEditTxt
import kotlinx.android.synthetic.main.activity_sign_up.pwEditTxt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.security.MessageDigest

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpBtn.setOnClickListener(signUpBtnClickListener)

    }

    private val signUpBtnClickListener: View.OnClickListener = View.OnClickListener {
        var id = idEditTxt.text.toString()
        var pw = pwEditTxt.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            var signUpStatus = signUp(id,pw)

            Log.d("signUpLog","${id}, ${pw}, ${signUpStatus}")
            if(signUpStatus.equals("success")){
                var intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
                finish()
            }
            else{
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "회원정보가 이미 존재합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private suspend fun signUp(id: String, pw: String): String{
        val call: Call<GetStatus> = DBServiceImpl.service.signUp(id,pw.toMD5())
        var statusCode = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
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
