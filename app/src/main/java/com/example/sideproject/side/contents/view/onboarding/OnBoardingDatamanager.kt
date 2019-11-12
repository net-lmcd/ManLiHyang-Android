package com.example.sideproject.side.contents.view.onboarding

import android.content.Context
import com.example.sideproject.side.contents.base.BaseDataManager
import com.example.sideproject.side.contents.local.preference.PreferenceManager
import com.example.sideproject.side.contents.rx.AppSchedulerProvider

class OnBoardingDatamanager(val context: Context, private val preferenceManager: PreferenceManager )
    : BaseDataManager(context, preferenceManager), OnBoardingDataManagerImpl{
}