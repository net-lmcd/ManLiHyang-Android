package com.sideproject.manlihyang.side.contents.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.TypeofTab
import com.sideproject.manlihyang.side.contents.view.main.MainDataManager
import com.sideproject.manlihyang.side.contents.view.main.MainNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sideproject.manlihyang.side.contents.model.main.Board

class MainViewModel<N : MainNavigator>(
    schedulerProvider: SchedulerProvider)
    : BaseViewModel<N>(schedulerProvider) {

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

    fun getBoardsOfUser() {
        boardList.value = listOf(
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0),
            Board("","","","",0,0)
        )
    }
}