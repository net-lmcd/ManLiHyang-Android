package com.sideproject.manlihyang.side.contents.local.preference

import android.content.Context
import android.content.SharedPreferences
import com.sideproject.manlihyang.BuildConfig
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.remote.model.User

class PreferenceManager : PreferenceHelper {

    /**
     *  from PreferenceHelper
     */

    override fun getAccessToken(): AuthResponse? { return null }
    override fun setAccessToken(accessToken: String) {}

    override fun isSignedIn(): Boolean? { return false }
    override fun setSignedIn(signedIn: Boolean) {}

    override fun getUser(): User? { return null }
    override fun setUser(user: User) {}


    fun remove(key : Key) { mPref!!.edit().remove(key.name).apply() }
    fun clear() = mPref!!.edit().clear().commit()

    constructor(context : Context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    @Synchronized
    fun getInstance(context: Context): PreferenceManager {
        if (mInstance == null) {
            mInstance = PreferenceManager(context)
        }
        return mInstance!!
    }

    enum class Key {
        accessToken, refreshToken, user
    }

    companion object {
        const val PREF_NAME = BuildConfig.APPLICATION_ID + ".local"
        var mInstance : PreferenceManager? = null
        var mPref : SharedPreferences? = null
    }
}