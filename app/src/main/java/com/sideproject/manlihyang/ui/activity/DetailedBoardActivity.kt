package com.sideproject.manlihyang.ui.activity

import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityDetailedBoardBinding
import com.sideproject.manlihyang.base.BaseActivity

class DetailedBoardActivity : BaseActivity<ActivityDetailedBoardBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_detailed_board

    override fun initViewModel() {}
    override fun initView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
