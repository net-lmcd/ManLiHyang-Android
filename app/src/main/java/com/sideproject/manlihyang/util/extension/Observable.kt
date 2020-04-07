package com.sideproject.manlihyang.util.extension

import androidx.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

val<T> ObservableField<T>.rx : Observable<T> get() {
        var observer : androidx.databinding.Observable.OnPropertyChangedCallback? = null
        return Observable.create { emitter : ObservableEmitter<T> ->
            observer = object : androidx.databinding.Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(
                    sender: androidx.databinding.Observable?,
                    propertyId: Int
                ) {
                    emitter.onNext(get()!!)
                }
            }
            this.addOnPropertyChangedCallback(observer!!)
        }.doOnDispose {
            this.removeOnPropertyChangedCallback(observer!!)
        }
}
