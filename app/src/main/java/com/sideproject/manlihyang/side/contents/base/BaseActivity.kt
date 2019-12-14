package com.sideproject.manlihyang.side.contents.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sideproject.manlihyang.side.contents.util.Move
import com.sideproject.manlihyang.side.contents.util.Keyboard
import com.sideproject.manlihyang.side.contents.widget.CircularProgress

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), BaseNavigator {

    lateinit var binding : T
    lateinit var loading : CircularProgress

    protected abstract fun getLayoutId() : Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        loading = CircularProgress(this)
    }

    /**
     *   Activity to be moved with sth...
     */

    fun openNextActivity(nextActivity: Class<*>) {

        startActivity(Intent(this, nextActivity))
    }

    override fun openNextActivity(move: Move) {

        try {
            openNextActivity(Class.forName(move.getName()))
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun openNextActivityClearTop(move: Move) {

        try {
            val intent = Intent(this, Class.forName(move.getName()))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun openNextActivityFinish(move: Move) {

        try {
            val intent = Intent(this, Class.forName(move.getName()))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    /**
     *  Can use functions for Keyboard
     */

    fun showKeyboard() {
        Keyboard.showKeyboard(this)
    }

    fun hideKeyboard() {
        Keyboard.hideKeyboard(this)
    }

    fun hideKeyboardChildAswell(view : View) {
        Keyboard.hideKeyboardChildAswell(view, this)
    }

    /**
     *  Can use loading with a dialog
     */

    override fun showLoading() {
        runOnUiThread {
            loading.show()
        }
    }

    override fun hideLoading() {
        loading.dismiss()
    }

    override fun handleError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogMessage(message: Int) {
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