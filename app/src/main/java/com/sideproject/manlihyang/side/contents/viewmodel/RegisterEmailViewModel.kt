package com.sideproject.manlihyang.side.contents.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationRequest
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.UserCreateRequest
import com.sideproject.manlihyang.side.contents.remote.model.User
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.MessageDialogClickListener
import com.sideproject.manlihyang.side.contents.util.Validation
import com.sideproject.manlihyang.side.contents.util.extension.combinelivedata
import com.sideproject.manlihyang.side.contents.util.extension.map
import com.sideproject.manlihyang.side.contents.util.extension.rx
import com.sideproject.manlihyang.side.contents.util.extension.validate
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager
import io.reactivex.exceptions.CompositeException
import java.util.concurrent.TimeUnit

class RegisterEmailViewModel<N : BaseNavigator>(
    private val onBoardingDatamanager: OnBoardingDatamanager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(schedulerProvider) {

    val nickname = MutableLiveData<String>("")
    val email = MutableLiveData<String>("")
    val emailNotify = MutableLiveData<String>("")
    val observableEmail : ObservableField<String> = ObservableField("")
    val password = MutableLiveData<String>("")
    val passwordChecked = MutableLiveData<String>("")
    var policyChecked = MutableLiveData<Boolean>(false)

    val isNotBlankNickname = nickname.map {
        it?.isNotBlank()
    }
    val isNotBlankEmail = email.map {
        it?.isNotBlank()
    }
    val isNotBlankPassword = password.map {
        it?.isNotBlank()
    }
    val isNotBlankPasswordCheck = passwordChecked.map {
        it?.isNotBlank()
    }

    val isServicableEmail = MutableLiveData<Boolean>(false)
    val isValidEmail = email.map {
        it?.validate(Validation.EMAIL) ?: false
    }
    val isValidPassword = password.map {
        it?.validate(Validation.PASSWORD) ?: false
    }
    val isSamePassword = passwordChecked.map {
        it?.equals(password.value) ?: false
    }

    val isValidRegister = isNotBlankNickname.combinelivedata(
        isServicableEmail,
        isValidPassword,
        isSamePassword,
        policyChecked,
        default = null
    ) { nick, email, password, passwordchecked, policychecked ->
        nick!! &&
                email!! &&
                password!! &&
                passwordchecked!! && policychecked!!
    }

    fun duplicationChecked() {
        val inputEmail : String = email.value ?: return
        if(isValidEmail.value!! && isNotBlankEmail.value!!) {
            compositeDisposable.add(
                onBoardingDatamanager.checkForDuplication(
                    EmailDuplicationRequest(
                        service_code = 1000,
                        email = inputEmail
                    )
                )
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.main())
                    .subscribe({
                        isServicableEmail.value = true
                        getNavigator().showDialogMessage("사용 가능한 이메일 입니다")
                    }, {
                        if(it is HttpException) when(it.code()) {
                            409 ->
                                getNavigator().showDialogMessage("이미 사용하고 있는 이메일 입니다")
                            500 ->
                                getNavigator().showDialogMessage("잠시후 다시 시도해주세요")
                            else ->
                                getNavigator().showDialogMessage("${it.message()}")
                        }
                    })
            )
        } else {
            getNavigator().showDialogMessage("이메일을 다시 한 번 확인해 주세요.")
        }
    }

   /* init {
        compositeDisposable.add(
            observableEmail.rx.debounce(700, TimeUnit.MILLISECONDS)
                .subscribe {
                    onBoardingDatamanager.checkforduplication(it)
                        .doOnError {
                            getNavigator().showDialogMessage(it.message!!)
                        }
                        .subscribe ({
                            email.value = this.observableEmail.get()
                            if(isValidEmail.value!!) {
                                emailNotify.value = "사용가능한 이메일 입니다."
                            } else {
                                if(isNotBlankEmail.value!!) {
                                    emailNotify.value = "이메일 형식이 옳지 않습니다."
                                } else {
                                    emailNotify.value = ""
                                }
                            }
                        }, {
                            email.value = this.observableEmail.get()
                            if(it is CompositeException) {
                                Log.e("compositeException", "${it.localizedMessage}")
                                Log.e("compositeException", "${it.exceptions}")
                                Log.e("compositeException", "${it.cause}")
                                Log.e("compositeException", "${it.message}")

                            }
                            *//*
                            if(it is HttpException)  when(it.code()) {
                                409 ->
                                    emailNotify.value = "이미 사용중인 이메일 입니다."
                                400 ->
                                    emailNotify.value = "Bad Request."
                            }*//*
                        })
                }
        )
    }*/

    fun onNextClicked() {
        val inputNickname = nickname.value ?: return
        val inputEmail = email.value ?: return
        val inputPassword = password.value ?: return
        compositeDisposable.add(
            onBoardingDatamanager.createUser(
                UserCreateRequest(
                    username = inputNickname,
                    email = inputEmail,
                    password = inputPassword,
                    notice = false,
                    notice_chat = false
                )
            )
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({
                    getNavigator().showDialogMessageAndFinish("회원가입이 성공적으로 완료되었습니다")
                },{
                    getNavigator().showDialogMessage("회원가입에 실패하셨습니다")
                })
        )
    }
}