package com.sideproject.manlihyang.ui.activity

import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityPersonalConnectionBinding
import com.sideproject.manlihyang.base.BaseActivity
import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.ui.adapter.PersonalConnectionAdapter
import com.sideproject.manlihyang.ui.viewmodel.MoveVIewModel
import kotlinx.android.synthetic.main.activity_personal_connection.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalConnectionActivity : BaseActivity<ActivityPersonalConnectionBinding>() {

    private val moveViewModel : MoveVIewModel<BaseNavigator> by viewModel()

    override val layoutResId: Int
        get() = R.layout.activity_personal_connection

    override fun registerNavigator() {
        moveViewModel.setNavigator(this)
    }

    override fun initViewModel() {
    }

    override fun initView() {
        viewDataBinding.setVariable(BR.moveModel, moveViewModel)
    }

    override fun onResume() {
        super.onResume()

        pager.apply {
            adapter = PersonalConnectionAdapter(
                fragmentManager = supportFragmentManager,
                pageCount = 2
            )
        }. also {
            tab.setupWithViewPager(it)
        }

        back.setOnClickListener {
            onBackPressed()
        }

        //get info of user
        moveViewModel.getUser()
    }
}