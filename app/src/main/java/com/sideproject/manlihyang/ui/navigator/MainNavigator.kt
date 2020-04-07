package com.sideproject.manlihyang.ui.navigator

import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.util.TypeofTab

interface MainNavigator : BaseNavigator {
    fun onNavigationTabSelected(tab : TypeofTab) { }
}