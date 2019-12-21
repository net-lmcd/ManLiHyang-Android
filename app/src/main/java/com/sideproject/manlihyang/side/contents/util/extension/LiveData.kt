package com.sideproject.manlihyang.side.contents.util.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <T,R> LiveData<T>.map(
    defaultValue: R? = null,
    crossinline block: ((T?) -> R?)
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        value = defaultValue
        addSource(this@map) { item: T? ->
            this.value = block.invoke(item)
        }
    }
}

inline fun<T1,T2,T3,T4,R> LiveData<T1>.combinelivedata(
    second : LiveData<T2>,
    third : LiveData<T3>,
    fourth : LiveData<T4>,
    default : R? = null,
    crossinline block: (T1?, T2?, T3?, T4?) -> R?
) : LiveData<R> {
    return MediatorLiveData<R>().apply {
        value = default
        addSource(this@combinelivedata) { item : T1? ->
            this.value =
                block.invoke(item, second.value, third.value, fourth.value)
        }
        addSource(second) { item : T2? ->
            this.value =
                block.invoke(this@combinelivedata.value, item, third.value, fourth.value)
        }
        addSource(third) { item : T3? ->
            this.value =
                block.invoke(this@combinelivedata.value, second.value, item, fourth.value)
        }
        addSource(fourth) { item : T4? ->
            this.value =
                block.invoke(this@combinelivedata.value, second.value, third.value, item)
        }
    }
}
