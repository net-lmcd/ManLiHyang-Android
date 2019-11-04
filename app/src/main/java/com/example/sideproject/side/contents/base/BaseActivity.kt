package com.example.sideproject.side.contents.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.sideproject.side.contents.util.Intented

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), BaseNavigator {

    lateinit var binding : T

    protected abstract fun getLayoutId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())

    }

    fun openNextActivity(nextActivity: Class<*>) {

        startActivity(Intent(this, nextActivity))
    }

    override fun openNextActivity(intented: Intented) {

        try {
            openNextActivity(Class.forName(intented.getName()))
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun openNextActivityClearTop(intented: Intented) {

        try {
            val intent = Intent(this, Class.forName(intented.getName()))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun openNextActivityFinish(intented: Intented) {

        try {
            val intent = Intent(this, Class.forName(intented.getName()))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
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