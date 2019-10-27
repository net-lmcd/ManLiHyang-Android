package com.example.sideproject.side.contents.base

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
            /*is ApiException -> when (error.message) {
                "12501: " -> "취소되었습니다."
                else -> error.localizedMessage
            }
            is FirebaseAuthInvalidCredentialsException -> when (error.errorCode) {
                "ERROR_WRONG_PASSWORD" -> "입력하신 이메일 또는 비밀번호가 잘못되었습니다. 다시 입력해주세요."
                "ERROR_BLOCKED" -> "이용이 차단되었습니다."
                else -> error.localizedMessage
            }
            is FirebaseAuthInvalidUserException -> when (error.errorCode) {
                "ERROR_USER_NOT_FOUND" -> "가입되지 않은 이메일입니다. 회원가입 후 이용해주세요."
                else -> error.localizedMessage
            }
            is FirebaseAuthUserCollisionException -> "이미 다른 계정에서 사용중입니다."
            is ContactsNotFoundException -> "연락처가 없습니다."
            is BillingDisconnectedException -> "구글 서비스에 연결할 수 없습니다."
            is MatchedUserNotFoundException -> "매칭되는 사용자가 없습니다."
            is HttpException -> error.response().errorBody()?.string()?.let { errorJson: String ->
                Gson().fromJson<ErrorResponse>(errorJson, ErrorResponse::class.java)
            }?.errorInfo?.code?.let { errorCode: String ->
                when (errorCode) {
                    "auth/email-already-exists" -> "이미 다른 계정에서 사용중인 이메일입니다."
                    else -> errorCode
                }
            }*/

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