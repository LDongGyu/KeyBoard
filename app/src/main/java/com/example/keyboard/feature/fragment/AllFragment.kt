package com.example.keyboard.feature.fragment

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keyboard.feature.Item.ItemManageActivity
import com.example.keyboard.feature.KeyList.KeyItem
import com.example.keyboard.feature.KeyList.KeyListViewAdapter

import com.example.keyboard.R
import com.example.keyboard.api.DBServiceImpl
import com.example.keyboard.feature.Singleton.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AllFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllFragment : Fragment() {

    private lateinit var data: List<KeyItem>
    private lateinit var keyListAdapter: KeyListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        runBlocking {
            data = initData()
        }
        keyListAdapter = KeyListViewAdapter(data)

        var view = inflater.inflate(R.layout.fragment_all,container,false) // 뷰를 먼저 만들고
        var keyList = view.findViewById(R.id.allList) as RecyclerView
        keyList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        keyListAdapter.setItemClickListener(object : KeyListViewAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                var intent = Intent(context, ItemManageActivity::class.java)
                intent.putExtra("title",data[position].title)
                intent.putExtra("category",data[position].categroy)
                intent.putExtra("id",data[position].id)
                intent.putExtra("pw",data[position].pw)
                intent.putExtra("url",data[position].url)
                intent.putExtra("etc",data[position].etc)
                startActivity(intent)
            }
        })
        keyList.adapter = keyListAdapter // 데이터 등록하고 return

        return view
    }

    private suspend fun initData(): List<KeyItem>{
        lateinit var temp:List<KeyItem>
        val call: Call<List<KeyItem>> = DBServiceImpl.service.getItem(UserInfo.id)
        var job = CoroutineScope(Dispatchers.IO).launch {
            var result = call.execute()
            temp = result.body()!!
        }
        job.join()
        for(item in temp){
            item.icon = Icon.createWithResource(context,R.drawable.logo)
        }
        var data = temp

        return data
    }
}