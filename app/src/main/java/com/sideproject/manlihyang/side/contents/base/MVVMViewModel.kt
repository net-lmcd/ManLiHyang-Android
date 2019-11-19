package com.sideproject.manlihyang.side.contents.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


open class MVVMViewModel : ViewModel() {

    internal var compositeDisposable: CompositeDisposable = CompositeDisposable()

    internal val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    internal val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    internal val _successMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isLoading: LiveData<Boolean> = _isLoading
    val errorMessage: LiveData<String> = _errorMessage
    val successMessage: LiveData<String> = _successMessage

    @Suppress("UNUSED_PARAMETER")
    protected fun <T> startLoading(value: T) {
        startLoading()
    }

    protected fun startLoading() {
        _isLoading.value = true
    }

    @Suppress("UNUSED_PARAMETER")
    protected fun <T> handleSuccess(value: T) {
        handleSuccess()
    }

    protected fun handleSuccess() {
        _isLoading.value = false
    }

    protected fun handleError(error: Throwable) {
        Timber.d(error)

        _isLoading.value = false

        when (error) {

            is Exception -> when (error.message) {
                "user_cancel" -> "취소되었습니다."
                else -> error.localizedMessage
            }

            is NoSuchElementException -> "취소되었습니다."

            is KotlinNullPointerException -> "존재하지 않거나 삭제된 콘텐츠입니다."

            else -> error.localizedMessage

        }?.let { message ->
            _errorMessage.value = message
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun forceClear() {
        this.onCleared()
    }
}