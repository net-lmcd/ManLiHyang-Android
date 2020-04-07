package com.sideproject.manlihyang.util.extension

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

inline fun<T0,T1,R> LiveData<T0>.combinelivedata(
    first : LiveData<T1>,
    default: R? = null,
    crossinline block: (T0?, T1?) -> R?
) : LiveData<R> {
    return MediatorLiveData<R>().apply {
        value = default
        addSource(this@combinelivedata) { item : T0? ->
            this.value =
                block.invoke(item, first.value)
        }
        addSource(first) { item : T1? ->
            this.value =
                block.invoke(this@combinelivedata.value, item)
        }
    }
}

inline fun<T0,T1,T2,T3,T4,R> LiveData<T0>.combinelivedata(
    first : LiveData<T1>,
    second : LiveData<T2>,
    third : LiveData<T3>,
    fourth : LiveData<T4>,
    default : R? = null,
    crossinline block: (T0?, T1?, T2?, T3?, T4?) -> R?
) : LiveData<R> {
    return MediatorLiveData<R>().apply {
        value = default
        addSource(this@combinelivedata) { item : T0? ->
            this.value =
                block.invoke(item, first.value, second.value, third.value, fourth.value)
        }
        addSource(first) { item : T1? ->
            this.value =
                block.invoke(this@combinelivedata.value, item, second.value, third.value, fourth.value)
        }
        addSource(second) { item : T2? ->
            this.value =
                block.invoke(this@combinelivedata.value, first.value, item, third.value, fourth.value)
        }
        addSource(third) { item : T3? ->
            this.value =
                block.invoke(this@combinelivedata.value, first.value, second.value, item, fourth.value)
        }
        addSource(fourth) { item : T4? ->
            this.value =
                block.invoke(this@combinelivedata.value, first.value, second.value, third.value, item)
        }
    }
}
