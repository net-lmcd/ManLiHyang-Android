package com.sideproject.manlihyang.side.contents.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityRegisterAccountBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterAccountActivity : BaseActivity<ActivityRegisterAccountBinding>() {

    private val onBoardingViewModel : OnBoardingViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_register_account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set variable for binding
        binding.setVariable(BR.onBoardingModel, onBoardingViewModel)
    }
}
