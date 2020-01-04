package com.sideproject.manlihyang.side.contents.data.local

import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.data.remote.User

interface PreferenceHelper{

    fun getAccessToken() : AuthResponse?
    fun setAccessToken(accessToken: String)

    fun isSignedIn(): Boolean?
    fun setSignedIn(signedIn: Boolean)

    fun getUser(): User?
    fun setUser(user: User)
}