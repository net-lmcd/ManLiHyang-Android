package com.example.sideproject.side.contents.local.preference

import com.example.sideproject.side.contents.model.AuthResponse
import com.example.sideproject.side.contents.remote.model.User

interface PreferenceHelper{

    fun getAccessToken() : AuthResponse?
    fun setAccessToken(accessToken: String)

    fun isSignedIn(): Boolean?
    fun setSignedIn(signedIn: Boolean)

    fun getUser(): User?
    fun setUser(user: User)
}