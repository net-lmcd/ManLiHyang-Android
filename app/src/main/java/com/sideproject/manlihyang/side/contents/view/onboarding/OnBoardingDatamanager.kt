package com.sideproject.manlihyang.side.contents.view.onboarding

import android.content.Context
import com.kakao.auth.network.request.AuthRequest
import com.sideproject.manlihyang.side.contents.base.BaseDataManager
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceManager
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationRequest
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationResponse
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.UserCreateRequest
import com.sideproject.manlihyang.side.contents.repository.OnBoardingApi
import com.sideproject.manlihyang.side.contents.rx.SchedulerProvider
import com.sideproject.manlihyang.side.contents.util.ApiService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call

class OnBoardingDatamanager(
    val context: Context,
    private val schedulerProvider: SchedulerProvider,
    private val preferenceManager: PreferenceManager )
    : BaseDataManager(context, preferenceManager), OnBoardingDataManagerImpl {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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