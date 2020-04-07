package com.sideproject.manlihyang.ui.fragment.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.sideproject.manlihyang.BR

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentMypageBinding
import com.sideproject.manlihyang.base.BaseFragment
import com.sideproject.manlihyang.ui.activity.PersonalConnectionActivity
import com.sideproject.manlihyang.ui.adapter.MyPageAdapter
import com.sideproject.manlihyang.ui.navigator.MainNavigator
import com.sideproject.manlihyang.ui.fragment.BottomSheetFragment
import com.sideproject.manlihyang.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.actionbar.menu
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding, MainViewModel<MainNavigator>>()  {

    private val mainViewModel : MainViewModel<MainNavigator> by sharedViewModel()
    private val pagerAdapter : MyPageAdapter by lazy {
        MyPageAdapter(
            fragmentManager = childFragmentManager,
            pageCount = 2
        )
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

        follower_group.setOnClickListener {
            startActivity(Intent(it.context, PersonalConnectionActivity::class.java))
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

        BottomSheetFragment()
            .show(childFragmentManager,"bottomsheet")
    }

    companion object {
        fun instantiate() : MyPageFragment{
            return MyPageFragment()
        }
    }
}