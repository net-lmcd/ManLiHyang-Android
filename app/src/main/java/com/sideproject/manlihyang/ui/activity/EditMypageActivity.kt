package com.sideproject.manlihyang.ui.activity

import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityEditMypageBinding
import com.sideproject.manlihyang.base.BaseActivity
import com.sideproject.manlihyang.ui.viewmodel.MoveVIewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditMypageActivity : BaseActivity<ActivityEditMypageBinding, MoveVIewModel>() {

    private val mViewModel : MoveVIewModel by viewModel()

    override val viewModel: MoveVIewModel
        get() = mViewModel

    override val layoutResId: Int
        get() = R.layout.activity_edit_mypage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
