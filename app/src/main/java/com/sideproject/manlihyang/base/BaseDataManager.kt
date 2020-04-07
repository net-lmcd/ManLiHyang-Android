package com.sideproject.manlihyang.base

import android.content.Context
import com.sideproject.manlihyang.data.local.PreferenceHelper
import com.sideproject.manlihyang.data.local.PreferenceManager
import com.sideproject.manlihyang.model.local.AuthResponse
import com.sideproject.manlihyang.data.remote.User

open class BaseDataManager constructor(
    context: Context,
    private val preferenceManager: PreferenceManager
) : PreferenceHelper {

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