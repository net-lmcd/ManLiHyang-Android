package com.sideproject.manlihyang.di

import com.crashlytics.android.Crashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.data.local.PreferenceManager
import com.sideproject.manlihyang.util.rx.AppSchedulerProvider
import com.sideproject.manlihyang.util.rx.SchedulerProvider
import com.sideproject.manlihyang.ui.adapter.BoardAdapter
import com.sideproject.manlihyang.ui.adapter.FollowAdapter
import com.sideproject.manlihyang.ui.navigator.MainNavigator
import com.sideproject.manlihyang.data.OnBoardingDataSourceImpl
import com.sideproject.manlihyang.ui.viewmodel.MainViewModel
import com.sideproject.manlihyang.ui.viewmodel.MoveVIewModel
import com.sideproject.manlihyang.ui.viewmodel.OnBoardingViewModel
import com.sideproject.manlihyang.ui.viewmodel.RegisterEmailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

//single 전역으로 사용
val appModule : Module = module {

    single { Crashlytics() }
    single { AppSchedulerProvider() as SchedulerProvider }
    single { PreferenceManager(get()) }
    single {
        OnBoardingDataSourceImpl(
            get(),
            get(),
            get()
        )
    }
    single { FirebaseMessaging.getInstance() }
}

//viewModel, factory 사용
val viewModule : Module = module {

    factory { BoardAdapter() }
    factory { FollowAdapter() }
    viewModel {
        OnBoardingViewModel<BaseNavigator>(
            get()
        )
    }
    viewModel {
        MainViewModel<MainNavigator>(
            get()
        )
    }
    viewModel {
        MoveVIewModel<BaseNavigator>(
            get()
        )
    }
    viewModel {
        RegisterEmailViewModel<BaseNavigator>(
            get(),
            get()
        )
    }
}

val module = listOf(appModule, viewModule)
