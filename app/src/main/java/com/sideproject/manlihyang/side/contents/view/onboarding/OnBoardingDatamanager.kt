package com.sideproject.manlihyang.side.contents.view.onboarding

import android.content.Context
import com.sideproject.manlihyang.side.contents.base.BaseDataManager
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceManager
import com.sideproject.manlihyang.side.contents.rx.AppSchedulerProvider

class OnBoardingDatamanager(val context: Context, private val preferenceManager: PreferenceManager )
    : BaseDataManager(context, preferenceManager), OnBoardingDataManagerImpl{
}