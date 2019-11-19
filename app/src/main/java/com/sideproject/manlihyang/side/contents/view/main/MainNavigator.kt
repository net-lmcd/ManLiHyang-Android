package com.sideproject.manlihyang.side.contents.view.main

import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.util.TypeofTab

interface MainNavigator : BaseNavigator {

    fun onNavigationTabSelected(tab : TypeofTab) { }
}