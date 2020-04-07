package com.sideproject.manlihyang.ui.activity


import android.app.Activity
import android.content.Intent
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityRegisterEmailBinding
import com.sideproject.manlihyang.base.BaseActivity
import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.ui.adapter.QuestionAdapter
import com.sideproject.manlihyang.ui.viewmodel.RegisterEmailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterEmailActivity : BaseActivity<ActivityRegisterEmailBinding>() {

    private val registerViewModel : RegisterEmailViewModel<BaseNavigator> by viewModel()

    override val layoutResId: Int
        get() = R.layout.activity_register_email

    override fun registerNavigator() {
        registerViewModel.setNavigator(this)
    }

    override fun initViewModel() {

    }

    override fun initView() {
        viewDataBinding.setVariable(BR.registerModel, registerViewModel)
        viewDataBinding.spinner.adapter = QuestionAdapter(this@RegisterEmailActivity)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onResume() {
        super.onResume()

        /*val dialog = PolicyDialog(this)
        policy.setOnClickListener {
            dialog.show()
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            val window : Window = dialog.window!!
            window.attributes = lp
        }*/
        viewDataBinding.policy.setOnClickListener {
            startActivityForResult(
                Intent(applicationContext, PolicyPopupActivity::class.java), REQUEST_POPUP_CONFIRM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_POPUP_CONFIRM && resultCode == Activity.RESULT_OK) {
            registerViewModel.policyChecked.value = true
        }
    }

    companion object {
        const val REQUEST_POPUP_CONFIRM = 0x1111
    }
}
