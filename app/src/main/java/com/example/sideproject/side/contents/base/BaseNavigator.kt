package com.example.sideproject.side.contents.base

import android.content.Intent
import com.example.sideproject.side.contents.util.Intented
import com.example.sideproject.side.contents.util.MessageDialogClickListener

interface BaseNavigator {

    /**
     * @param intented: Open activity depending on intented
     * @see Intented
     */
    fun openNextActivity(intented: Intented)


    /**
     * @param intented: Open activity depending on intented and finish
     * @see Intented
     */
    fun openNextActivityFinish(intented: Intented)

    /**
     * @param intented: Open activity depending on intented and finish
     * @see Intented
     */
    fun openNextActivityClearTop(intented: Intented)


    /**
     * @param message is a text to show popup dialog
     * @see BaseNavigator
     */
    fun showDialogMessage(message: String)

    /**
     * @param message is a text to show popup dialog
     * @see BaseNavigator
     */
    open fun showLoginRequiredDialogMessage(message: String) {

    }

    /**
     * @param message is int as id of string in string xml
     * @see BaseNavigator
     */
    fun showDialogMessage(message: Int)


    /**
     * @param message is int as id of string in string xml
     * @see BaseNavigator
     */
    open fun showLoginRequiredDialogMessage(message: Int) {

    }


    /**
     * @param throwable
     */
    fun handleError(throwable: Throwable)


    /**
     * show loading dialog on UI
     */
    fun showLoading()

    /**
     * hide loading dialog on UI
     */
    fun hideLoading()

    /**
     * @param message is a text to show popup dialog and when click done finish activity
     * @see BaseNavigator
     */
    fun showDialogMessageAndFinish(message: String)

    fun onRefreshData()

    fun finishActivityFromViewModel()

    fun showSelectRequiredDialogMessage(s: String, messageDialogClickListener: MessageDialogClickListener){

    }

    fun showDialogMessage(s: String, messageDialogClickListener: MessageDialogClickListener) {

    }

    fun showConfirmDialogMessage(s: String, messageDialogClickListener: MessageDialogClickListener) {

    }

    fun showConfirmDialogMessage(
        s: String,
        buttonText: String,
        messageDialogClickListener: MessageDialogClickListener
    ) {

    }
}
