package com.sideproject.manlihyang.side.contents.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.side.contents.util.Move
import com.sideproject.manlihyang.side.contents.util.Keyboard
import com.sideproject.manlihyang.side.contents.widget.CircularProgress
import kotlinx.android.synthetic.main.actionbar.*
import java.util.*

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), BaseNavigator {

    lateinit var binding : T
    lateinit var loading : CircularProgress

    private var mActionToolbar : Toolbar? = null
    private var actionbar_title : TextView? = null

    protected abstract fun initViewModel()
    protected abstract fun initView()
    protected abstract fun getLayoutId() : Int

    open fun hasActionBar(): Boolean = false
    open fun hasBackIcon(): Boolean = false
    open fun hasMoreImage() : Boolean = false
    open fun hasEdit() : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        loading = CircularProgress(this)

        initViewModel()
        setActionBar()
        initView()
    }

    fun setActionBar() {
        if (hasActionBar()) {
            mActionToolbar = binding.root.findViewById(R.id.actionBar)
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

            if(hasMoreImage()) {
                select.apply {
                    visibility = View.VISIBLE

                }
            }

            if(hasEdit()) {
                edit.apply {
                    visibility = View.VISIBLE
                }
            }
        }
    }

    override fun backActivity() {
        onBackPressed()
    }

    override fun nextActivity(move: Move) {

        startActivity(Intent(this, Class.forName(move.getName())))
    }

    override fun nextActivityClearTop(move: Move) {

        try {
            val intent = Intent(this, Class.forName(move.getName()))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun nextActivityFinish(move: Move) {

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