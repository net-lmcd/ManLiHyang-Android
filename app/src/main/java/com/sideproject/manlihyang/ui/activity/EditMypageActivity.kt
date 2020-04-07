package com.sideproject.manlihyang.ui.activity

import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityEditMypageBinding
import com.sideproject.manlihyang.base.BaseActivity

class EditMypageActivity : BaseActivity<ActivityEditMypageBinding>() {

    override fun hasActionBar(): Boolean = true
    override fun hasBackIcon(): Boolean = true
    override val layoutResId: Int
        get() = R.layout.activity_edit_mypage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {}
    override fun initView() {}

}
