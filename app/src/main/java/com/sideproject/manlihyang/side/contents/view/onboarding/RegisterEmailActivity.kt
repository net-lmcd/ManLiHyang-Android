package com.sideproject.manlihyang.side.contents.view.onboarding

import android.view.View
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityRegisterEmailBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.RegisterEmailViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterEmailActivity : BaseActivity<ActivityRegisterEmailBinding>() {

    private val registerViewModel : RegisterEmailViewModel<BaseNavigator> by viewModel()
    private val onBoardingViewModel : OnBoardingViewModel<BaseNavigator> by viewModel()
    private val moveViewMode : MoveVIewModel<BaseNavigator> by viewModel()

    override fun hasActionBar(): Boolean = true
    override fun hasBackIcon(): Boolean = true
    override fun hasEdit(): Boolean = false
    override fun hasMoreImage(): Boolean = false

    override fun getLayoutId(): Int = R.layout.activity_register_email
    override fun initViewModel() {
        registerViewModel.setNavigator(this)
    }

    override fun initView() {
        binding.setVariable(BR.registerModel, registerViewModel)
    }
}
