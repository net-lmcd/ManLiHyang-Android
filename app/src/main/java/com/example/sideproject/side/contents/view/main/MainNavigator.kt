package com.example.sideproject.side.contents.view.main

import com.example.sideproject.side.contents.base.BaseNavigator
import com.example.sideproject.side.contents.util.TypeofTab

interface MainNavigator : BaseNavigator {

    fun onNavigationTabSelected(tab : TypeofTab) { }
}