package com.example.keyboard.feature.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.keyboard.R
import com.example.keyboard.feature.Category.CategoryListActivity
import com.example.keyboard.feature.Login.ChangePwActivity
import com.example.keyboard.feature.Login.SignIn.SignInActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SettingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_setting, container, false)
        view.logoutBtn.setOnClickListener(logoutBtnClickListener)
        view.changePwBtn.setOnClickListener(changeBtnClickListener)
        view.categoryEditBtn.setOnClickListener(categoryEditBtnClickListener)
        return view
    }

    private val logoutBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(context,
            SignInActivity::class.java))
        activity?.finish()
    }

    private val changeBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(context,ChangePwActivity::class.java))
    }

    private val categoryEditBtnClickListener: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(context,CategoryListActivity::class.java))
    }
}
