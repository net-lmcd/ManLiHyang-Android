package com.sideproject.manlihyang.side.contents.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.sideproject.manlihyang.side.contents.util.Move

class BaseNavigatorImpl(
    private val activity: Activity
) : BaseNavigator {

    override fun openNextActivity(move: Move) {
        activity?.startActivity(Intent(activity, Class.forName(move.getName())))
    }

    override fun openNextActivityFinish(move: Move) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openNextActivityClearTop(move: Move) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogMessage(message: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogMessageAndFinish(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefreshData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishActivityFromViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}