package com.news.sample.ui.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentDialogBinding

class LoadingDialog : DialogFragment() {

    lateinit var viewDataBinding: FragmentDialogBinding

    override fun onStart() {
        super.onStart()
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog, container, false)
        return viewDataBinding.root
    }

    companion object {
        const val TAG = "LoadingDialog"
        fun instantiate() : LoadingDialog {
            return LoadingDialog()
        }
    }
}