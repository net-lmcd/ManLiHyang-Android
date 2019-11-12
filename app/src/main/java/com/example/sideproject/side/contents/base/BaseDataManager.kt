package com.example.sideproject.side.contents.base

import android.content.Context
import com.example.sideproject.side.contents.local.preference.PreferenceManager
import com.example.sideproject.side.contents.model.AuthResponse
import com.example.sideproject.side.contents.remote.model.User

open class BaseDataManager constructor(context: Context, private val preferenceManager: PreferenceManager) : BaseDataManagerImpl {

    override fun getUser(): User {
        return preferenceManager.getUser()!!
    }

    override fun setUser(user: User) {
        preferenceManager.setUser(user)
    }

    override fun getAccessToken(): AuthResponse {
        return preferenceManager.getAccessToken()!!
    }

    override fun setAccessToken(accessToken: String) {
        preferenceManager.setAccessToken(accessToken)
    }

    override fun isSignedIn(): Boolean? {
        return preferenceManager.isSignedIn()
    }

    override fun setSignedIn(signedIn: Boolean) {
        preferenceManager.setSignedIn(signedIn)
    }

}