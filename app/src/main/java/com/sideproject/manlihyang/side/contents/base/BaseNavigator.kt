package com.sideproject.manlihyang.side.contents.base

import com.sideproject.manlihyang.side.contents.util.Move
import com.sideproject.manlihyang.side.contents.util.MessageDialogClickListener

interface BaseNavigator {

    /**
     * @param move: Open activity depending on move
     * @see Move
     */
    fun nextActivity(move: Move)

    /**
     * @param move: Open activity depending on move and finish
     * @see Move
     */
    fun nextActivityFinish(move: Move)

    /**
     * @param move: Open activity depending on move and finish
     * @see Move
     */
    fun nextActivityClearTop(move: Move)

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
