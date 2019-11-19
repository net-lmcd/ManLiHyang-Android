package com.sideproject.manlihyang.side.contents.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val onBoardingViewModel : OnBoardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //just including login UI on .xml
    }
}
