package com.sideproject.manlihyang.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BaseViewModel<N : BaseNavigator>: ViewModel() {

    private lateinit var mNavigator: WeakReference<N>
    protected val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference<N>(navigator)
    }

    fun getNavigator(): N {
        return mNavigator.get()!!
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}