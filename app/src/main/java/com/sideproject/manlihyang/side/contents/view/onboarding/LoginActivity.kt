package com.sideproject.manlihyang.side.contents.view.onboarding

import android.os.Bundle
import android.view.View
import com.crashlytics.android.Crashlytics
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityLoginBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.view.main.MainNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val mCrashlytics : Crashlytics by inject()
    override fun getLayoutId(): Int = R.layout.activity_login

    private val onBoardingViewModel : OnBoardingViewModel by viewModel()
    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set variable for binding
        binding.setVariable(BR.onBoardingModel, onBoardingViewModel)
    }

    fun onClick(view : View) {
        hideKeyboardChildAswell(view)
    }

    override fun onResume() {
        super.onResume()
        onBoardingViewModel.setNavigator(this)
    }
}
