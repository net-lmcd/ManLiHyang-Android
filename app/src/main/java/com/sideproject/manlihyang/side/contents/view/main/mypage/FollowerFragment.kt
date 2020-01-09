package com.sideproject.manlihyang.side.contents.view.main.mypage

import android.os.Bundle
import android.view.View
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentFollowerBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.view.adapter.FollowAdapter
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FollowerFragment : BaseFragment<FragmentFollowerBinding, MoveVIewModel<BaseNavigator>>() {

    private val moveVIewModel : MoveVIewModel<BaseNavigator> by sharedViewModel()
    private val followAdapter : FollowAdapter by inject()

    override fun getViewModel(): MoveVIewModel<BaseNavigator> = moveVIewModel
    override fun getBindingVariable(): Int = BR.moveModel
    override fun getLayoutId(): Int = R.layout.fragment_follower

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            adapter = followAdapter
        }
    }

    companion object {
        fun instantiate() : FollowerFragment {
            return FollowerFragment()
        }
    }
}