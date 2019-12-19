package com.sideproject.manlihyang.side.contents.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.base.BaseNavigatorImpl
import com.sideproject.manlihyang.side.contents.base.BaseViewModel
import com.sideproject.manlihyang.side.contents.remote.model.User
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.Move
import com.sideproject.manlihyang.side.contents.view.onboarding.OnBoardingDatamanager

class OnBoardingViewModel<N : BaseNavigator>(
    schedulerProvider: SchedulerProvider
) : BaseViewModel<N>(schedulerProvider) {

    val _user : MutableLiveData<User> by lazy { MutableLiveData<User>() }
    var email = MutableLiveData<String>("")
    var name = MutableLiveData<String>("")
    var passwordFirst = MutableLiveData<String>("")
    var passwordSecond = MutableLiveData<String>("")
    var check = MutableLiveData<Boolean>(false)

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

    fun test() {
    }

    fun toRegisterEmailActivity() {
        getNavigator().nextActivity(Move.ToRegisterEmailActivity)
    }

    fun toMainActivity() {
        getNavigator().nextActivity(Move.ToMainActivity)
    }

    fun textChanged() : TextWatcher = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}