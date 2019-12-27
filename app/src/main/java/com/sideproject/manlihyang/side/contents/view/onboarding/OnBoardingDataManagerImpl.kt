package com.sideproject.manlihyang.side.contents.view.onboarding

import com.sideproject.manlihyang.side.contents.base.BaseDataManagerImpl
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationRequest
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationResponse
import com.sideproject.manlihyang.side.contents.repository.OnBoardingApi
import io.reactivex.Observable

/**
     *  OnBoarding API 상속 부분
     */

interface OnBoardingDataManagerImpl: BaseDataManagerImpl, OnBoardingApi {

    fun updateUserInfo()
    fun checkforduplication(email : String) : Observable<EmailDuplicationResponse>
}