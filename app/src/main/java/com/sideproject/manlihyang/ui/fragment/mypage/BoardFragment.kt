package com.sideproject.manlihyang.ui.fragment.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.base.BaseFragment
import com.sideproject.manlihyang.databinding.FragmentBoardBinding
import com.sideproject.manlihyang.ui.activity.DetailedBoardActivity
import com.sideproject.manlihyang.ui.adapter.BoardAdapter
import com.sideproject.manlihyang.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BoardFragment : BaseFragment<FragmentBoardBinding, MainViewModel>() {

    private val mainViewModel : MainViewModel by sharedViewModel()
    private val boardAdapter : BoardAdapter by inject()

    override fun getViewModel(): MainViewModel = mainViewModel
    override fun getBindingVariable(): Int = BR.mainModel
    override fun getLayoutId(): Int = R.layout.fragment_board

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            adapter = boardAdapter.apply {
                setOnItemSelectedListener { item, position ->
                    startActivity(Intent(activity!!.applicationContext, DetailedBoardActivity::class.java))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getBoardsOfUser()
    }

    companion object {
        fun instantiate() : BoardFragment {
            return BoardFragment()
        }
    }
}