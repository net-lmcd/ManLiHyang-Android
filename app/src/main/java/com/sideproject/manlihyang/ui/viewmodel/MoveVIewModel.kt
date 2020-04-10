package com.sideproject.manlihyang.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sideproject.manlihyang.base.BaseNavigator
import com.sideproject.manlihyang.base.BaseViewModel
import com.sideproject.manlihyang.data.remote.User
import com.sideproject.manlihyang.model.main.Follow
import com.sideproject.manlihyang.util.rx.SchedulerProvider

class MoveVIewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel<BaseNavigator>() {

    var user : MutableLiveData<User> = MutableLiveData()

    fun getUser() {
        user.value = User(
            id = 1,
            usn = "1234",
            name = "cheolhunchoi",
            email = "salls123@naver.com",
            notice = false,
            notice_chat = false,
            profile_url = "",
            is_blocked = false,
            report_cnt = 0,
            follower = listOf(
                Follow(1,"DongUkHong","","",""),
                Follow(2,"SangMinLee","","",""),
                Follow(3,"SangEunLee","","",""),
                Follow(4,"TaeJunKim","","",""),
                Follow(5,"JinWooPark","","",""),
                Follow(6,"SeongJinLee","","",""),
                Follow(7,"HoGyungDo","","",""),
                Follow(8,"SangJinKim","","",""),
                Follow(9,"MyungSupYong","","",""),
                Follow(10,"TaeHeePark","","",""),
                Follow(11,"YongSunLee","","",""),
                Follow(12,"XungHeeKim","","",""),
                Follow(13,"YoonHaLim","","",""),
                Follow(14,"JiMinChoi","","","")),
            following = listOf(
                Follow(0,"DongJinLim","","",""),
                Follow(1,"KilJunChoi","","",""),
                Follow(2,"YoonTaeSim","","",""))
        )
    }
}