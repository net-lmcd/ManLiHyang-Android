package com.sideproject.manlihyang.side.contents.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityAfterSplashBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import org.koin.android.ext.android.inject

class AfterSplashActivity : BaseActivity<ActivityAfterSplashBinding>(), BaseNavigator {

    private val moveViewModel : MoveVIewModel<BaseNavigator> by inject()

    override fun getLayoutId(): Int = R.layout.activity_after_splash
    override fun initViewModel() {
        moveViewModel.setNavigator(this)
    }
    override fun initView() {
        binding.setVariable(BR.moveModel, moveViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
