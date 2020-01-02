package com.sideproject.manlihyang.side.contents.view.main


import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentMypageBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import kotlinx.android.synthetic.main.actionbar.*

class MyPageFragment : BaseFragment<FragmentMypageBinding>()  {

    override fun getLayoutId() = R.layout.fragment_mypage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu.apply {
            visibility = View.VISIBLE
            setOnClickListener{
                menuClick(it)
            }
        }
    }

    fun menuClick(view : View) {
        val popup = PopupMenu(activity?.applicationContext, view)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.bottom_nav_menu, popup.menu)
        popup.show()
        //colorstatusBar(true)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.navigation_content -> {
                    Toast.makeText(activity?.applicationContext, "content", Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_mypage -> {
                    Toast.makeText(activity?.applicationContext, "mypage", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    companion object {
      fun instantiate() : MyPageFragment{
          return MyPageFragment()
      }
    }
}
