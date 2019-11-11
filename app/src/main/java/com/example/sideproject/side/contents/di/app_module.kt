package com.example.sideproject.side.contents.di

import android.graphics.Point
import com.example.sideproject.side.contents.rx.AppSchedulerProvider
import com.example.sideproject.side.contents.rx.SchedulerProvider
import com.example.sideproject.side.contents.view.main.MainDataManager
import com.example.sideproject.side.contents.view.onboarding.OnBoardingDatamanager
import com.example.sideproject.side.contents.viewmodel.MainViewModel
import com.example.sideproject.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

//single 전역으로 사용
val appModule : Module = module {

    single { AppSchedulerProvider() as SchedulerProvider }
    single { OnBoardingDatamanager() }
    single { MainDataManager() }

}

//viewModel, factory 사용
val viewModule : Module = module {

    viewModel { OnBoardingViewModel(get(), get())}
    viewModel { MainViewModel(get(), get())}
}

val module = listOf(appModule, viewModule)
