package com.sideproject.manlihyang.side.contents.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationRequest
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.UserCreateRequest
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.Validation
import com.sideproject.manlihyang.side.contents.util.extension.combinelivedata
import com.sideproject.manlihyang.side.contents.util.extension.map
import com.sideproject.manlihyang.side.contents.util.extension.rx
import com.sideproject.manlihyang.side.contents.util.extension.validate
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager
import java.util.concurrent.TimeUnit

class RegisterEmailViewModel<N : BaseNavigator>(
    private val onBoardingDatamanager: OnBoardingDatamanager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(schedulerProvider) {

    val email = MutableLiveData<String>("")
    val emailNotify = MutableLiveData<String>("")
    val observableEmail : ObservableField<String> = ObservableField("")
    val password = MutableLiveData<String>("")
    val passwordChecked = MutableLiveData<String>("")
    var policyChecked = MutableLiveData<Boolean>(false)

    val isNotBlankEmail = email.map {
        it?.isNotBlank()
    }
    val isNotBlankPassword = password.map {
        it?.isNotBlank()
    }
    val isNotBlankPasswordCheck = passwordChecked.map {
        it?.isNotBlank()
    }

    val isValidEmail = email.map {
        it?.validate(Validation.EMAIL) ?: false
    }
    val isValidPassword = password.map {
        it?.validate(Validation.PASSWORD) ?: false
    }
    val isSamePassword = passwordChecked.map {
        it?.equals(password.value) ?: false
    }

    val isValidRegister = isValidEmail.combinelivedata(
        isValidPassword,
        isSamePassword,
        policyChecked,
        default = null
    ) { email, password, passwordchecked, policychecked ->
        email!! &&
                password!! &&
                passwordchecked!! && policychecked!!
    }

    fun duplicationChecked() {
        val inputEmail = email.value ?: return
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
                    Log.e("test","success")
                }, {
                Log.e("test","failed")
            })
        )
    }

    init {
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
                            if(it is HttpException)  when(it.code()) {
                                409 ->
                                    emailNotify.value = "이미 사용중인 이메일 입니다."
                                400 ->
                                    emailNotify.value = "Bad Request."
                            }
                        })
                }
        )
    }

    fun onNextClicked() {
        //duplicationChecked()
        //getNavigator().backActivity()

        kotlin.run {
            val inputEmail = email.value ?: return
            val inputPassword = password.value ?: return
            compositeDisposable.add(
                onBoardingDatamanager.createUser(
                    UserCreateRequest(
                        username = "최철훈",
                        email = inputEmail,
                        password = inputPassword,
                        notice = false,
                        notice_chat = false
                    )
                )
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.main())
                    .subscribe({
                        getNavigator().showDialogMessage("회원가입이 성공적으로 완료되었습니다.")
                        getNavigator().backActivity()
                    },{
                        getNavigator().showDialogMessage("회원가입에 실패하셨습니다.")
                    })
            )
        }
    }
}