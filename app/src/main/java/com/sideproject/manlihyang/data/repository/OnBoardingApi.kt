package com.sideproject.manlihyang.data.repository

import com.kakao.auth.network.request.AuthRequest
import com.sideproject.manlihyang.model.local.AuthResponse
import com.sideproject.manlihyang.model.onboarding.EmailDuplicationRequest
import com.sideproject.manlihyang.model.onboarding.EmailDuplicationResponse
import com.sideproject.manlihyang.model.onboarding.UserCreateRequest
import io.reactivex.Completable
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

    @POST("users/1000/confirm")
    fun checkForDuplication(@Body emailDuplicationRequest: EmailDuplicationRequest) : Single<EmailDuplicationResponse>



/*    @POST("/auth/register")
    fun register(@Body userCreate: UserCreateRequest) : Single<AuthResponse>

    @PUT("/auth")
    fun putPassword(@Body putPassword: PutPasswordRequest): Completable

    @PUT("/auth/reset")
    fun resetPassword(@Body resetPassword: ResetPassword): Completable*/
}