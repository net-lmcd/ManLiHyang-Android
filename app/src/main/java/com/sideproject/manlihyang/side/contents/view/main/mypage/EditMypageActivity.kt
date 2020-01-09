package com.sideproject.manlihyang.side.contents.view.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityEditMypageBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity

class EditMypageActivity : BaseActivity<ActivityEditMypageBinding>() {

    override fun hasActionBar(): Boolean = true
    override fun hasBackIcon(): Boolean = true
    override fun hasEdit(): Boolean = false
    override fun getLayoutId(): Int = R.layout.activity_edit_mypage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {}
    override fun initView() {}

}
