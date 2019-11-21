package com.sideproject.manlihyang.side.contents.view.onboarding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityLoginBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_login

    private val onBoardingViewModel : OnBoardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.onBoardingModel, onBoardingViewModel)
        //just including login UI on .xml
    }
}
