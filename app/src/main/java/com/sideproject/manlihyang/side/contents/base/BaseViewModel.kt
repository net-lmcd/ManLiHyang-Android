package com.sideproject.manlihyang.side.contents.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference


open class BaseViewModel<T>(val schedulerProvider: SchedulerProvider) : ViewModel() {

    var loading = ObservableBoolean(false)
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var mNavigator: WeakReference<T>

    fun setNavigator(navigator: T) {
        mNavigator = WeakReference<T>(navigator)
    }

    fun getNavigator(): T {
        return mNavigator.get()!!
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    fun isLoading(): ObservableBoolean {
        return loading
    }

    fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }
}