package com.sideproject.manlihyang.data

import android.content.Context
import com.kakao.auth.network.request.AuthRequest
import com.sideproject.manlihyang.base.BaseDataManager
import com.sideproject.manlihyang.data.local.PreferenceManager
import com.sideproject.manlihyang.model.local.AuthResponse
import com.sideproject.manlihyang.model.onboarding.EmailDuplicationRequest
import com.sideproject.manlihyang.model.onboarding.EmailDuplicationResponse
import com.sideproject.manlihyang.model.onboarding.UserCreateRequest
import com.sideproject.manlihyang.data.repository.OnBoardingApi
import com.sideproject.manlihyang.util.rx.SchedulerProvider
import com.sideproject.manlihyang.util.ApiService
import com.sideproject.manlihyang.data.source.OnBoardingDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call

class OnBoardingDataSourceImpl(
    val context: Context,
    private val schedulerProvider: SchedulerProvider,
    private val preferenceManager: PreferenceManager
) : BaseDataManager(context, preferenceManager), OnBoardingDataSource {

    private val onBoardingApi = ApiService.provideApi(OnBoardingApi::class.java, context)
    private val onBoardingApiWithoutAuth = ApiService.provideApiWithoutAuth(OnBoardingApi::class.java)

    override fun createUser(userCreateRequest: UserCreateRequest): Completable {
        return onBoardingApi.createUser(userCreateRequest)
    }

    override fun updateUserInfo() {
        preferenceManager.setSignedIn(true)
    }

    override fun login(authRequest: AuthRequest): Single<AuthResponse> {
        return onBoardingApi.login(authRequest)
    }

    override fun refreshToken(token: AuthResponse): Call<AuthResponse> {
        return onBoardingApi.refreshToken(token)
    }

    override fun checkForDuplication(emailDuplicationRequest: EmailDuplicationRequest): Single<EmailDuplicationResponse> {
        return onBoardingApi.checkForDuplication(emailDuplicationRequest)
    }

    override fun checkforduplication(email : String): Observable<EmailDuplicationResponse> {
        return onBoardingApi.checkForDuplication(
            EmailDuplicationRequest(
                service_code = 1000,
                email = email
            )
        )
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
            .toObservable()
    }
}