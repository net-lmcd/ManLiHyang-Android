package com.sideproject.manlihyang.side.contents.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.remote.model.User
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.Intented
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager

class OnBoardingViewModel constructor(
    private var onBoardingDatamanager: OnBoardingDatamanager,
    schedulerProvider: SchedulerProvider)
    : BaseViewModel<BaseNavigator>(schedulerProvider) {

    val _user : MutableLiveData<User> by lazy { MutableLiveData<User>() }
    var email = MutableLiveData<String>("")
    var name = MutableLiveData<String>("")
    var passwordFirst = MutableLiveData<String>("")
    var passwordSecond = MutableLiveData<String>("")


//    val isValidEmail: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply{
//        addSource(email){
//            this.value = it.va
//        }
//    }

 /*   fun check() {
        Log.e("check", "${email.value} + ${password.value}")
        Log.e("check", Validation.isValidOrNot(email.value.toString(), type = Validation.CheckType.Email).toString())
        Log.e("password", Validation.isValidOrNot(password.value.toString(), type = Validation.CheckType.Password).toString())
        Log.e("phonenumber", Validation.isValidOrNot("01012345678", type = Validation.CheckType.PhoneNumber).toString())
    }*/

    fun toRegisterAccountActivity() {
        getNavigator().openNextActivity(Intented.ToRegisterAccountActivity)
    }
}