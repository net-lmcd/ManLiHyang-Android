package com.example.sideproject.side.contents.viewmodel

import android.util.Log
import com.example.sideproject.side.contents.base.BaseViewModel
import com.example.sideproject.side.contents.base.MVVMViewModel
import com.example.sideproject.side.contents.rx.SchedulerProvider
import com.example.sideproject.side.contents.view.onboarding.OnBoardingDatamanager
import io.reactivex.Flowable

class OnBoardingViewModel constructor(private var onBoardingDatamanager: OnBoardingDatamanager,
    private var schedulerProvider: SchedulerProvider) : MVVMViewModel() {

}