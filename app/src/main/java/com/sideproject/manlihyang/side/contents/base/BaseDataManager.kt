package com.sideproject.manlihyang.side.contents.base

import android.content.Context
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceHelper
import com.sideproject.manlihyang.side.contents.local.preference.PreferenceManager
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.remote.model.User

open class BaseDataManager constructor(context: Context, private val preferenceManager: PreferenceManager) : PreferenceHelper {

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