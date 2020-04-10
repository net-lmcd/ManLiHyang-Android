package com.sideproject.manlihyang.base

import android.app.Activity
import android.os.Bundle
import com.sideproject.manlihyang.util.dialog.MessageDialogClickListener
import kotlin.reflect.KClass

interface BaseNavigator {

    fun backActivity()
    fun <T: Activity> nextActivity(kClass: KClass<T>, bundle: Bundle? = null, clearTask: Boolean = false)

    fun showDialogMessage(message: String, listener: MessageDialogClickListener? = null)

    fun showLoading()
    fun hideLoading()
}
