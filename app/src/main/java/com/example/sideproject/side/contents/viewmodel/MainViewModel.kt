package com.example.sideproject.side.contents.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.sideproject.R
import com.example.sideproject.side.contents.base.BaseViewModel
import com.example.sideproject.side.contents.base.MVVMViewModel
import com.example.sideproject.side.contents.rx.SchedulerProvider
import com.example.sideproject.side.contents.util.TypeofTab
import com.example.sideproject.side.contents.view.main.MainDataManager
import com.example.sideproject.side.contents.view.main.MainNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainViewModel constructor(val mainDataManager: MainDataManager, schedulerProvider: SchedulerProvider)
    : BaseViewModel<MainNavigator>(schedulerProvider) {

    var currentTab = TypeofTab.Content

    val navigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener
        get() = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_content -> currentTab = TypeofTab.Content
                R.id.navigation_chat -> currentTab = TypeofTab.Chat
                R.id.navigation_write -> currentTab = TypeofTab.Write
                R.id.navigation_mypage -> currentTab = TypeofTab.Mypage
            }
            getNavigator().onNavigationTabSelected(currentTab)
            true
        }
}