package com.sideproject.manlihyang.side.contents.di

import com.crashlytics.android.Crashlytics
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceManager
import com.sideproject.manlihyang.side.contents.rx.AppSchedulerProvider
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.view.main.MainDataManager
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

//single 전역으로 사용
val appModule : Module = module {

    single { Crashlytics() }
    single { AppSchedulerProvider() as SchedulerProvider }
    single { PreferenceManager(get()) }
    single { OnBoardingDatamanager(get(), get()) }
    single { MainDataManager() }

}

//viewModel, factory 사용
val viewModule : Module = module {

    viewModel { OnBoardingViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get()) }
}

val module = listOf(appModule, viewModule)
