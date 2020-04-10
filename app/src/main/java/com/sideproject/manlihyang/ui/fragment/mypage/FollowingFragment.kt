package com.sideproject.manlihyang.ui.fragment.mypage

import android.os.Bundle
import android.view.View
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentFollowingBinding
import com.sideproject.manlihyang.base.BaseFragment
import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.ui.adapter.FollowAdapter
import com.sideproject.manlihyang.ui.viewmodel.MoveVIewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FollowingFragment : BaseFragment<FragmentFollowingBinding, MoveVIewModel>() {

    private val moveVIewModel : MoveVIewModel by sharedViewModel()
    private val followAdapter : FollowAdapter by inject()

    override fun getViewModel(): MoveVIewModel = moveVIewModel
    override fun getBindingVariable(): Int = BR.moveModel
    override fun getLayoutId(): Int = R.layout.fragment_following

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            adapter = followAdapter
        }
    }

    companion object {
        fun instantiate() : FollowingFragment {
            return FollowingFragment()
        }
    }
}