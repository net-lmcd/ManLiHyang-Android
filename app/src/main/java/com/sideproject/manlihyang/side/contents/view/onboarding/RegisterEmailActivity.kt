package com.sideproject.manlihyang.side.contents.view.onboarding

import android.app.Activity
import android.content.Intent
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityRegisterEmailBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseSpinnerAdapter
import com.sideproject.manlihyang.side.contents.view.adapter.QuestionAdapter
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.RegisterEmailViewModel
import kotlinx.android.synthetic.main.activity_register_email.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterEmailActivity : BaseActivity<ActivityRegisterEmailBinding>() {

    private val registerViewModel : RegisterEmailViewModel<BaseNavigator> by viewModel()
    private val onBoardingViewModel : OnBoardingViewModel<BaseNavigator> by viewModel()
    private val moveViewMode : MoveVIewModel<BaseNavigator> by viewModel()

    override fun hasActionBar(): Boolean = true
    override fun hasBackIcon(): Boolean = true
    override fun hasEdit(): Boolean = false
    override fun hasMoreImage(): Boolean = false

    override fun getLayoutId(): Int = R.layout.activity_register_email
    override fun initViewModel() {
        registerViewModel.setNavigator(this)
    }

    override fun initView() {
        binding.setVariable(BR.registerModel, registerViewModel)
        spinner.adapter = QuestionAdapter(this@RegisterEmailActivity)

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
        policy.setOnClickListener {
            startActivityForResult(
                Intent(applicationContext, PolicyPopupActivity::class.java), REQUEST_POPUP_CONFIRM)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_POPUP_CONFIRM &&
                resultCode == Activity.RESULT_OK) {
            registerViewModel.policyChecked.value = true
        }
    }

    companion object {
        const val REQUEST_POPUP_CONFIRM = 0x1111
    }
}
