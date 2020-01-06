package com.sideproject.manlihyang.side.contents.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sideproject.manlihyang.side.contents.util.Move
import com.sideproject.manlihyang.side.contents.widget.CircularProgress

abstract class  BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    lateinit var loading : CircularProgress
    var binding : T? = null

    fun getBind() = binding!!
    protected abstract fun getViewModel() : V
    protected abstract fun getBindingVariable() : Int
    @LayoutRes
    abstract fun getLayoutId() : Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = getLayoutId()
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding!!.lifecycleOwner = this
        binding!!.setVariable(getBindingVariable(), getViewModel())
        binding!!.executePendingBindings()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = CircularProgress(this.context!!)
    }

    fun finish() {
        activity?.finish()
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }

    fun showLoading() {
        activity?.runOnUiThread {
            loading.show()
        }
    }

    fun hideLoading() {
        loading.dismiss()
    }
}