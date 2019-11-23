package com.sideproject.manlihyang.side.contents.base

import java.lang.ref.WeakReference

open class Navigator<T> {

    private lateinit var mNavigator : WeakReference<T>

    fun setNavigator(navigator : T) {
        mNavigator = WeakReference<T>(navigator)
    }

    fun getNavigator() : T {
        return mNavigator.get()!!
    }
}