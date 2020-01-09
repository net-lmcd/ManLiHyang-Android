package com.sideproject.manlihyang.side.contents.view.main.mypage

import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityPersonalConnectionBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.view.adapter.PersonalConnectionAdapter
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import kotlinx.android.synthetic.main.activity_personal_connection.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalConnectionActivity : BaseActivity<ActivityPersonalConnectionBinding>() {

    private val moveViewModel : MoveVIewModel<BaseNavigator> by viewModel()
    private val personalConnAdapter : PersonalConnectionAdapter by lazy {
        PersonalConnectionAdapter(fragmentManager = supportFragmentManager, pageCount = 2)
    }

    override fun hasActionBar(): Boolean = false
    override fun hasBackIcon(): Boolean = false
    override fun hasEdit(): Boolean = false
    override fun getLayoutId(): Int = R.layout.activity_personal_connection

    override fun initViewModel() {
        moveViewModel.setNavigator(this)
    }

    override fun initView() {
        binding.setVariable(BR.moveModel, moveViewModel)
    }

    override fun onResume() {
        super.onResume()

        pager.apply {
            adapter = personalConnAdapter
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
