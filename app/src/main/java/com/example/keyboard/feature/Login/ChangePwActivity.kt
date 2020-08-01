package com.example.keyboard.feature.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.data.GetStatus
import com.example.keyboard.data.PwChange
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.android.synthetic.main.activity_change_pw.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.security.MessageDigest

class ChangePwActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)

        saveBtn.setOnClickListener(saveBtnClickListener)
    }

    private val saveBtnClickListener: View.OnClickListener = View.OnClickListener {
        val id = UserInfo.id
        val currentPw = pwEditTxt.text.toString().toMD5()
        val newPw = newPwEditTxt.text.toString().toMD5()
        val data = PwChange(id,currentPw,newPw)

        CoroutineScope(Dispatchers.IO).launch {
            var status = changePw(data)
            Log.d("status",status)
            if(status.equals("success")){
                finish()
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"비밀번호 변경에 실패하였습니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun changePw(data: PwChange): String {
        var status = "default"

        var job = CoroutineScope(Dispatchers.IO).launch {
            var call: Call<GetStatus> = DBServiceImpl.service.pwChange(data)
            var response = call.execute()
            status = response.body()!!.status
        }
        job.join()
        return status
    }

    private fun String.toMD5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray()).joinToString("") { "%02x".format(it) }
        return bytes
    }
}
