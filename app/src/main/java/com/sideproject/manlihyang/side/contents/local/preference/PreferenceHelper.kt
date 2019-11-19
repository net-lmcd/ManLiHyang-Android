package com.sideproject.manlihyang.side.contents.local.preference

import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.remote.model.User

interface PreferenceHelper{

    fun getAccessToken() : AuthResponse?
    fun setAccessToken(accessToken: String)

    fun isSignedIn(): Boolean?
    fun setSignedIn(signedIn: Boolean)

    fun getUser(): User?
    fun setUser(user: User)
}