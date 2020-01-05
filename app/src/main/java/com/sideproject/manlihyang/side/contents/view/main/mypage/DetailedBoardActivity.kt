package com.sideproject.manlihyang.side.contents.view.main.mypage

import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityDetailedBoardBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity

class DetailedBoardActivity : BaseActivity<ActivityDetailedBoardBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_detailed_board

    override fun initViewModel() {}
    override fun initView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
