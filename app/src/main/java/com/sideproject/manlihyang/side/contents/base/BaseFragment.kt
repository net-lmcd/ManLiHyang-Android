package com.sideproject.manlihyang.side.contents.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    var binding : T? = null

    @LayoutRes
    abstract fun getLayoutId() : Int

    public fun getBind() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layoutId = getLayoutId()

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding!!.lifecycleOwner = this
        binding!!.executePendingBindings()

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun finish() {
        activity?.finish()
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }

}