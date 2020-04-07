package com.sideproject.manlihyang.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.news.sample.ui.view.fragment.LoadingDialog
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.util.Keyboard
import com.sideproject.manlihyang.util.MessageDialogClickListener
import com.sideproject.manlihyang.util.CircularProgress
import com.sideproject.manlihyang.util.Dialog
import java.util.*
import kotlin.reflect.KClass

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity(), BaseNavigator {

    lateinit var loading : CircularProgress

    private var mActionToolbar : Toolbar? = null
    private var actionbar_title : TextView? = null

    protected abstract fun initViewModel()
    protected abstract fun initView()

    @LayoutRes
    protected open val layoutResId: Int = 0

    protected lateinit var viewDataBinding: VDB

    open fun hasActionBar(): Boolean = false
    open fun hasBackIcon(): Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNavigator()
        bindView()
        loading = CircularProgress(this)
        initViewModel()
        setActionBar()
        initView()
    }

    private fun bindView() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResId)
        viewDataBinding.apply {
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
    }

    open fun registerNavigator() {}

    fun setActionBar() {
        if (hasActionBar()) {
            mActionToolbar = viewDataBinding.root.findViewById(R.id.actionBar)
            actionbar_title = mActionToolbar?.findViewById<View>(R.id.bar_title) as? TextView

            if (mActionToolbar != null) {
                setSupportActionBar(mActionToolbar)
                Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(false)
                supportActionBar!!.setHomeButtonEnabled(false)
                supportActionBar!!.setDisplayShowTitleEnabled(false)

                if (hasBackIcon()) {
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                    supportActionBar!!.setHomeButtonEnabled(true)
                    supportActionBar!!.setDisplayShowTitleEnabled(false)
                    supportActionBar!!.setHomeAsUpIndicator(R.drawable.button_back_black)
                    mActionToolbar!!.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
                }
            }
        }
    }

    override fun backActivity() {
        onBackPressed()
    }

    override fun <T : Activity> nextActivity(kClass: KClass<T>, bundle: Bundle?, clearTask: Boolean) {
        startActivity(Intent(this, kClass.java).apply {
            bundle?.let(this::putExtras)
            if(clearTask) {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }
        })
    }

    fun showKeyboard() {
        Keyboard.showKeyboard(this)
    }

    fun hideKeyboard() {
        Keyboard.hideKeyboard(this)
    }

    fun hideKeyboardChildAswell(view : View) {
        Keyboard.hideKeyboardChildAswell(view, this)
    }

    override fun showLoading() {
        val existFragment  = supportFragmentManager
            .findFragmentByTag(LoadingDialog.TAG) as? DialogFragment

        if(existFragment == null) {
            LoadingDialog.instantiate()
                .show(supportFragmentManager, LoadingDialog.TAG)
        } else {
            if(!existFragment.showsDialog) {
                existFragment
                    .show(supportFragmentManager, LoadingDialog.TAG)
            }
        }
    }

    override fun hideLoading() {
        val existFragment = supportFragmentManager
            .findFragmentByTag(LoadingDialog.TAG) as? LoadingDialog
            ?: return

        if(existFragment.showsDialog) {
            existFragment.dismiss()
        }
    }

    override fun showDialogMessage(message: String) {
        Dialog.showMessage(this, supportFragmentManager, message)
    }

    override fun showDialogMessage(message: Int) {
        Dialog.showMessage(this, supportFragmentManager, getString(message))
    }

    override fun showDialogMessageAndFinish(message: String) {
        Dialog.showMessage(this, supportFragmentManager, message)
            .setMessageDialogClickListener(object : MessageDialogClickListener {
                override fun confirmClick() { finish() }
            })
    }
}