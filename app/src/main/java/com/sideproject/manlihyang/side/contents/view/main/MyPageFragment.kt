package com.sideproject.manlihyang.side.contents.view.main


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import com.sideproject.manlihyang.BR

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentMypageBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import com.sideproject.manlihyang.side.contents.util.OnItemSelectedListener
import com.sideproject.manlihyang.side.contents.view.adapter.BoardAdapter
import com.sideproject.manlihyang.side.contents.view.onboarding.LoginActivity
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.actionbar.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding, MainViewModel<MainNavigator>>()  {

    private val mainViewModel : MainViewModel<MainNavigator> by sharedViewModel()
    private val boardadapter : BoardAdapter by inject()
    override fun getLayoutId() = R.layout.fragment_mypage
    override fun getViewModel(): MainViewModel<MainNavigator> = mainViewModel
    override fun getBindingVariable(): Int = BR.mainModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu.apply {
            visibility = View.VISIBLE
            setOnClickListener{
                menuClick(it)
            }
        }

        binding?.run {
            adapter = boardadapter.apply {
                setOnItemSelectedListener { item, position ->
                    startActivity(Intent(this@MyPageFragment.context, LoginActivity::class.java))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getBoardsOfUser()
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
