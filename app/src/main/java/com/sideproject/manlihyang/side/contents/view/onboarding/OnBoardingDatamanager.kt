package com.sideproject.manlihyang.side.contents.view.onboarding

import android.content.Context
import com.kakao.auth.network.request.AuthRequest
import com.sideproject.manlihyang.side.contents.base.BaseDataManager
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceManager
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationCheck
import com.sideproject.manlihyang.side.contents.repository.OnBoardingApi
import com.sideproject.manlihyang.side.contents.util.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call

class OnBoardingDatamanager(
    val context: Context,
    private val preferenceManager: PreferenceManager )
    : BaseDataManager(context, preferenceManager), OnBoardingDataManagerImpl {

    private val onBoardingApi = ApiService.provideApi(OnBoardingApi::class.java, context)
    private val onBoardingApiWithoutAuth = ApiService.provideApiWithoutAuth(OnBoardingApi::class.java)

    override fun login(authRequest: AuthRequest): Single<AuthResponse> {
        return onBoardingApi.login(authRequest)
    }

    override fun refreshToken(token: AuthResponse): Call<AuthResponse> {
        return onBoardingApi.refreshToken(token)
    }

    override fun checkForDuplication(emailDuplicationCheck: EmailDuplicationCheck): Completable {
        return onBoardingApi.checkForDuplication(emailDuplicationCheck)
    }
}