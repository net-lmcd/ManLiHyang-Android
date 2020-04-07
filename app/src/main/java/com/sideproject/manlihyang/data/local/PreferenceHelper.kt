package com.sideproject.manlihyang.data.local

import com.sideproject.manlihyang.model.local.AuthResponse
import com.sideproject.manlihyang.data.remote.User

interface PreferenceHelper{

    fun getAccessToken() : AuthResponse?
    fun setAccessToken(accessToken: String)

    fun isSignedIn(): Boolean?
    fun setSignedIn(signedIn: Boolean)

    fun getUser(): User?
    fun setUser(user: User)
}