package com.sideproject.manlihyang.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.FragmentActivity
import com.sideproject.manlihyang.R
import kotlinx.android.synthetic.main.activity_policy_popup.*

class PolicyPopupActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_policy_popup)

        confirm.setOnClickListener {
            //to register screen
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}

