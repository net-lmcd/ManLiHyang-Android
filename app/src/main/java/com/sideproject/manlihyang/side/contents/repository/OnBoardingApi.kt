package com.sideproject.manlihyang.side.contents.repository

import com.kakao.auth.network.request.AuthRequest
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationRequest
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.EmailDuplicationResponse
import com.sideproject.manlihyang.side.contents.model.onboardingmodels.UserCreateRequest
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface OnBoardingApi {

    @POST("users/1000")
    fun createUser(@Body userCreateRequest: UserCreateRequest) : Completable

    @POST("/auth")
    fun login(@Body authRequest: AuthRequest) : Single<AuthResponse>

    @POST("auth/refresh")
    fun refreshToken(@Body token: AuthResponse): Call<AuthResponse>

    @POST("users/confirm")
    fun checkForDuplication(@Body emailDuplicationRequest: EmailDuplicationRequest) : Single<EmailDuplicationResponse>

/*    @POST("/auth/register")
    fun register(@Body userCreate: UserCreateRequest) : Single<AuthResponse>

    @PUT("/auth")
    fun putPassword(@Body putPassword: PutPasswordRequest): Completable

    @PUT("/auth/reset")
    fun resetPassword(@Body resetPassword: ResetPassword): Completable*/
}