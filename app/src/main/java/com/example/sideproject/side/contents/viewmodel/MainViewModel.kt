package com.example.sideproject.side.contents.viewmodel

import com.example.sideproject.side.contents.base.BaseViewModel
import com.example.sideproject.side.contents.base.MVVMViewModel
import com.example.sideproject.side.contents.rx.SchedulerProvider
import com.example.sideproject.side.contents.view.main.MainDataManager

class MainViewModel constructor(val mainDataManager: MainDataManager, schedulerProvider: SchedulerProvider)
    : MVVMViewModel() {

}