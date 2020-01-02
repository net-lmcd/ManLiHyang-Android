package com.sideproject.manlihyang.side.contents.di

import android.app.Activity
import com.crashlytics.android.Crashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseNavigatorImpl
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceManager
import com.sideproject.manlihyang.side.contents.rx.AppSchedulerProvider
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.view.main.MainDataManager
import com.sideproject.manlihyang.side.contents.view.main.MainNavigator
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.RegisterEmailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

//single 전역으로 사용
val appModule : Module = module {

    single { Crashlytics() }
    single { AppSchedulerProvider() as SchedulerProvider }
    single { PreferenceManager(get()) }
    single { MainDataManager(get(), get(), get()) }
    single { OnBoardingDatamanager(get(), get(), get()) }
    single { FirebaseMessaging.getInstance() }
}

//viewModel, factory 사용
val viewModule : Module = module {

    viewModel { OnBoardingViewModel<BaseNavigator>(get()) }
    viewModel { MainViewModel<MainNavigator>(get()) }
    viewModel { MoveVIewModel<BaseNavigator>(get()) }
    viewModel { RegisterEmailViewModel<BaseNavigator>(get(), get()) }
}

val module = listOf(appModule, viewModule)
