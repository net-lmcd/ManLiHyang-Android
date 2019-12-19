package com.sideproject.manlihyang.side.contents.viewmodel

import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.Move

class MoveVIewModel <N : BaseNavigator>(
    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(schedulerProvider) {

    fun toLoginActivity() {
        getNavigator().nextActivity(Move.ToLoginActivity)
    }

    fun toRegisterEmailActivity() {
        getNavigator().nextActivity(Move.ToRegisterEmailActivity)
    }
}