package com.sideproject.manlihyang.side.contents.view.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentPeoplePageBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import com.sideproject.manlihyang.side.contents.view.adapter.BoardAdapter
import com.sideproject.manlihyang.side.contents.view.main.MainNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PeoplePageFragment : BaseFragment<FragmentPeoplePageBinding, MainViewModel<MainNavigator>>() {

    private val mainViewModel : MainViewModel<MainNavigator> by sharedViewModel()
    private val boardadapter : BoardAdapter by inject()
    override fun getViewModel(): MainViewModel<MainNavigator> = mainViewModel
    override fun getBindingVariable(): Int = BR.mainModel
    override fun getLayoutId(): Int = R.layout.fragment_people_page

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            adapter = boardadapter.apply{
                setOnItemSelectedListener { item, position ->
                    startActivity(Intent(this@PeoplePageFragment.context, DetailedBoardActivity::class.java))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getBoardsOfUser()
    }

    companion object {
        fun instantiate() : PeoplePageFragment{
            return PeoplePageFragment()
        }
    }
}