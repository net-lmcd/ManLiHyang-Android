package com.sideproject.manlihyang.side.contents.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.Move
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference


open class BaseViewModel<N : BaseNavigator>(val schedulerProvider: SchedulerProvider) : ViewModel() {

    var loading = ObservableBoolean(false)
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mNavigator: WeakReference<N>

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference<N>(navigator)
    }

    fun getNavigator(): N {
        return mNavigator.get()!!
    }

    fun isLoading(): ObservableBoolean {
        return loading
    }

    fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }

    /**
     * @param disposable: You can add your disposable with the below method and
     *                    then cleared when its activity destroy
     * @see Move
     */

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}