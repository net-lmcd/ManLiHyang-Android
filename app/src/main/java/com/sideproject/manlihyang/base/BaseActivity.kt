package com.sideproject.manlihyang.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.news.sample.ui.view.fragment.LoadingDialog
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.util.Keyboard
import com.sideproject.manlihyang.util.dialog.Dialog
import com.sideproject.manlihyang.util.dialog.MessageDialogClickListener
import kotlin.reflect.KClass

abstract class BaseActivity<VDB : ViewDataBinding, VM: BaseViewModel<*>>
    : AppCompatActivity(), BaseNavigator {

    @LayoutRes
    protected open val layoutResId: Int = 0
    protected open val viewModelId: Int = BR.viewModel

    protected abstract val viewModel: VM
    protected lateinit var viewDataBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNavigator()
        bindView()
    }

    private fun bindView() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResId)
        viewDataBinding.apply {
            lifecycleOwner = this@BaseActivity
            setBindingVariables()
            executePendingBindings()
        }
    }

    protected open fun registerNavigator() {}

    protected open fun setBindingVariables() {
        viewDataBinding.setVariable(viewModelId, viewModel)
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

    override fun showDialogMessage(message: String, listener: MessageDialogClickListener?) {
        Dialog.showMessage(
            context = this,
            fragmentManager = supportFragmentManager,
            message = message,
            extra = true,
            listener = listener
        )
    }
}