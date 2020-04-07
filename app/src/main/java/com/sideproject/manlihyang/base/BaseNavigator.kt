package com.sideproject.manlihyang.base

import android.app.Activity
import android.os.Bundle
import kotlin.reflect.KClass

interface BaseNavigator {

    fun backActivity()
    fun <T: Activity> nextActivity(kClass: KClass<T>, bundle: Bundle? = null, clearTask: Boolean = false)

    fun showDialogMessage(message: String)
    fun showDialogMessage(message: Int)
    fun showDialogMessageAndFinish(message: String)

    fun showLoading()
    fun hideLoading()
}
