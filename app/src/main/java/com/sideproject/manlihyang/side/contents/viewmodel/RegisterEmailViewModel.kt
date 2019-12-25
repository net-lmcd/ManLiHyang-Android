package com.sideproject.manlihyang.side.contents.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationCheck
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.Validation
import com.sideproject.manlihyang.side.contents.util.extension.combinelivedata
import com.sideproject.manlihyang.side.contents.util.extension.map
import com.sideproject.manlihyang.side.contents.util.extension.validate
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager

class RegisterEmailViewModel<N : BaseNavigator>(
    private val onBoardingDatamanager: OnBoardingDatamanager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(schedulerProvider) {

    val email = MutableLiveData<String>("")
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
        it?.validate(Validation.EMAIL)
    }
    val isValidPassword = password.map {
        it?.validate(Validation.PASSWORD)
    }
    val isSamePassword = passwordChecked.map {
        it?.equals(password.value)
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
                EmailDuplicationCheck(
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

    fun onNextClicked() {
        duplicationChecked()
        //getNavigator().backActivity()
    }
}