package com.sideproject.manlihyang.side.contents.view.onboarding

import android.os.Bundle
import android.view.View
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityRegisterAccountBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterAccountActivity : BaseActivity<ActivityRegisterAccountBinding>() {

    private val onBoardingViewModel : OnBoardingViewModel<BaseNavigator> by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_register_account

    override fun initViewModel() {
        onBoardingViewModel.setNavigator(this)
    }

    override fun initView() {
        binding.setVariable(BR.onBoardingModel, onBoardingViewModel)
    }

    fun hideKeyboardFromEditText(view : View) {
        hideKeyboardChildAswell(view)
    }

}
