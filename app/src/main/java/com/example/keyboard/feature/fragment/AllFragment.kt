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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var data = initData()
        var keyListAdapter = KeyListViewAdapter(data)

        var view = inflater.inflate(R.layout.fragment_all,container,false) // 뷰를 먼저 만들고
        var keyList = view.findViewById(R.id.allList) as RecyclerView
        keyList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        keyListAdapter.setItemClickListener(object : KeyListViewAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                startActivity(Intent(context,
                    ItemManageActivity::class.java))
            }
        })
        keyList.adapter = keyListAdapter // 데이터 등록하고 return

        return view
    }

    private fun initData(): List<KeyItem>{
        var icon = Icon.createWithResource(context,R.drawable.logo)
        var temp1 = KeyItem(icon,"네이버","홈페이지")
        var temp2 = KeyItem(icon,"카카오","SNS")
        var temp3 = KeyItem(icon, "인스타그램", "SNS")
        var temp4 = KeyItem(icon, "본스", "피씨방")
        var data = listOf(temp1,temp2,temp3,temp4)

        return data
    }
}