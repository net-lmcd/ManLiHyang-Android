package com.sideproject.manlihyang.side.contents.view.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sideproject.manlihyang.BR

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentMypageBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import com.sideproject.manlihyang.side.contents.view.adapter.MyPageAdapter
import com.sideproject.manlihyang.side.contents.view.main.mypage.BottomSheetFragment
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.actionbar.menu
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding, MainViewModel<MainNavigator>>()  {

    private val mainViewModel : MainViewModel<MainNavigator> by sharedViewModel()
    private val pagerAdapter : MyPageAdapter by lazy {
        MyPageAdapter(fragmentManager = childFragmentManager, pageCount = 2)
    }

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
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getBoardsOfUser()

        pager.apply {
            adapter = pagerAdapter
        }.also {
            tab.setupWithViewPager(it)
        }
    }

    fun menuClick(view : View) {
        /*val popup = PopupMenu(activity?.applicationContext, view)
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
        }*/
       /* val sheet = BottomSheetBehavior.from(bottomSheet)
        val layoutParams= WindowManager.LayoutParams()
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND
        layoutParams.dimAmount= 0.7f
        activity!!.window.setAttributes(layoutParams)

        sheet.state = BottomSheetBehavior.STATE_COLLAPSED
        sheet.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    Log.e("asdf","asdf")
                }
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    Log.e("asdf","asdf")
                }
            }
        )*/

        BottomSheetFragment().show(childFragmentManager,"bottomsheet")
    }

    companion object {
      fun instantiate() : MyPageFragment{
          return MyPageFragment()
      }
    }
}
