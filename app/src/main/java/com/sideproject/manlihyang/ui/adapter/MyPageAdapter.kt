package com.sideproject.manlihyang.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sideproject.manlihyang.ui.fragment.mypage.BoardFragment
import com.sideproject.manlihyang.ui.fragment.mypage.StoreFragment

class MyPageAdapter (
    fragmentManager: FragmentManager,
    val pageCount : Int
) : FragmentStatePagerAdapter(fragmentManager, pageCount) {

    val boardFragment = BoardFragment.instantiate()
    val storeFragment = StoreFragment.instantiate()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "게시글"
            1 -> "저장글"
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment? = null
        fragment = when(position) {
            0 -> boardFragment
            1 -> storeFragment
            else -> null
        }
        return fragment!!
    }

    override fun getCount(): Int = pageCount
}