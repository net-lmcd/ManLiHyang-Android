package com.example.sideproject.side.contents.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.sideproject.side.contents.rx.SchedulerProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by Kaz on 08:43 8/20/18
 */
abstract class ViewModelB<N>(private var schedulerProvider: SchedulerProvider) : ViewModel() {

    private lateinit var navigator: WeakReference<N>
    var loading = ObservableBoolean(false)

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N = navigator.get()!!

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun launch(job: () -> Disposable) {
        compositeDisposable.add(job())
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    fun clear() {
        compositeDisposable.clear()
    }

    fun isLoading(): ObservableBoolean {
        return loading
    }

    fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }


}