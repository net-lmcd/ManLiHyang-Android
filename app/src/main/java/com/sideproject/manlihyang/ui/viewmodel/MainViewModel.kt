package com.sideproject.manlihyang.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.base.BaseViewModel
import com.sideproject.manlihyang.util.rx.SchedulerProvider
import com.sideproject.manlihyang.util.TypeofTab
import com.sideproject.manlihyang.ui.navigator.MainNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.model.main.Board

class MainViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>() {

    val bottomNaviSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener
        get() = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_content -> getNavigator().onNavigationTabSelected(TypeofTab.Content)
                R.id.navigation_chat -> getNavigator().onNavigationTabSelected(TypeofTab.Chat)
                R.id.navigation_write -> getNavigator().onNavigationTabSelected(TypeofTab.Write)
                R.id.navigation_mypage -> getNavigator().onNavigationTabSelected(TypeofTab.Mypage)
            }
            true
        }

    var boardList = MutableLiveData<List<Board>>()
    var storeList = MutableLiveData<List<Board>>()

    fun getBoardsOfUser() {
        boardList.value = listOf(
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0)
        )
    }

    fun getStoresOfUser() {
        storeList.value = listOf(
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0)
        )
    }
}