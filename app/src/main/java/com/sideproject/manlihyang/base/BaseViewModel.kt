package com.sideproject.manlihyang.base

import androidx.lifecycle.ViewModel
import com.sideproject.manlihyang.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BaseViewModel<N : BaseNavigator>(val schedulerProvider: SchedulerProvider) : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mNavigator: WeakReference<N>

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