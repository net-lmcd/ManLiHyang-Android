package com.sideproject.manlihyang.ui.activity

import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityPersonalConnectionBinding
import com.sideproject.manlihyang.base.BaseActivity
import com.sideproject.manlihyang.ui.adapter.PersonalConnectionAdapter
import com.sideproject.manlihyang.ui.viewmodel.MoveVIewModel
import kotlinx.android.synthetic.main.activity_personal_connection.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalConnectionActivity : BaseActivity<ActivityPersonalConnectionBinding, MoveVIewModel>() {

    private val mViewModel : MoveVIewModel by viewModel()

    override val viewModel: MoveVIewModel
        get() = mViewModel

    override val layoutResId: Int
        get() = R.layout.activity_personal_connection

    override fun registerNavigator() {
        viewModel.setNavigator(this)
    }

    override fun setBindingVariables() {
        super.setBindingVariables()
        viewDataBinding.setVariable(BR.moveModel, viewModel)
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
        viewModel.getUser()
    }
}