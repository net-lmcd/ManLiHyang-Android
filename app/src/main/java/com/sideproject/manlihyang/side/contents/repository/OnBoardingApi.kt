package com.sideproject.manlihyang.side.contents.repository

import com.kakao.auth.network.request.AuthRequest
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationCheck
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface OnBoardingApi {

    @POST("/auth")
    fun login(@Body authRequest: AuthRequest) : Single<AuthResponse>

    @POST("auth/refresh")
    fun refreshToken(@Body token: AuthResponse): Call<AuthResponse>

    @POST("users/confirm")
    fun checkForDuplication(@Body emailDuplicationCheck: EmailDuplicationCheck) : Completable

/*    @POST("/auth/register")
    fun register(@Body userCreate: UserCreateRequest) : Single<AuthResponse>

    @PUT("/auth")
    fun putPassword(@Body putPassword: PutPasswordRequest): Completable

    @PUT("/auth/reset")
    fun resetPassword(@Body resetPassword: ResetPassword): Completable*/
}