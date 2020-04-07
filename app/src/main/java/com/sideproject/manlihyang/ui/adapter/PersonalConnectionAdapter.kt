package com.sideproject.manlihyang.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sideproject.manlihyang.ui.fragment.FollowerFragment
import com.sideproject.manlihyang.ui.fragment.FollowingFragment

class PersonalConnectionAdapter (
    fragmentManager: FragmentManager,
    val pageCount : Int
) : FragmentStatePagerAdapter(fragmentManager, pageCount) {

    val followingFragment = FollowingFragment.instantiate()
    val followerFragment = FollowerFragment.instantiate()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "팔로워"
            1 -> "팔로우"
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment? = null
        fragment = when(position) {
            0 -> followerFragment
            1 -> followingFragment
            else -> null
        }
        return fragment!!
    }

    override fun getCount(): Int = pageCount
}