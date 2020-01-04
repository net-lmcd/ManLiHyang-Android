package com.sideproject.manlihyang.side.contents.view.main

import android.content.Context
import com.sideproject.manlihyang.side.contents.base.BaseDataManager
import com.sideproject.manlihyang.side.contents.data.local.PreferenceManager
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider

class MainDataManager(
    context: Context,
    private val schedulerProvider: SchedulerProvider,
    private val preferenceManager: PreferenceManager
) : BaseDataManager(context, preferenceManager) {
}