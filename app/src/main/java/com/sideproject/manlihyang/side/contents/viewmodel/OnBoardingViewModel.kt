package com.sideproject.manlihyang.side.contents.viewmodel

import android.util.Log
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.base.MVVMViewModel
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager
import io.reactivex.Flowable

class OnBoardingViewModel constructor(private var onBoardingDatamanager: OnBoardingDatamanager,
    private var schedulerProvider: SchedulerProvider) : MVVMViewModel() {

}