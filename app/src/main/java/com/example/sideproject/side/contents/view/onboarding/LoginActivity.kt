package com.example.sideproject.side.contents.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sideproject.R
import com.example.sideproject.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val onBoardingViewModel : OnBoardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //just including login UI on .xml
    }
}
