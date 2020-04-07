package com.sideproject.manlihyang.data.source

import com.sideproject.manlihyang.base.BaseDataManagerImpl
import com.sideproject.manlihyang.model.onboarding.EmailDuplicationResponse
import com.sideproject.manlihyang.data.repository.OnBoardingApi
import io.reactivex.Observable

interface OnBoardingDataSource: BaseDataManagerImpl, OnBoardingApi {

    fun updateUserInfo()
    fun checkforduplication(email : String) : Observable<EmailDuplicationResponse>
}