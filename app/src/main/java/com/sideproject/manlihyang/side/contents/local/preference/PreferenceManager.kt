package com.sideproject.manlihyang.side.contents.local.preference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sideproject.manlihyang.BuildConfig
import com.sideproject.manlihyang.side.contents.model.AuthResponse
import com.sideproject.manlihyang.side.contents.remote.model.User

class PreferenceManager : PreferenceHelper {

    override fun getAccessToken(): AuthResponse? {
        return Gson()
            .fromJson(getValue(String::class.java, Key.accessToken, "") as String, AuthResponse::class.java)
    }

    override fun setAccessToken(accessToken: String) {
        setValue(Key.accessToken, accessToken) }

    override fun isSignedIn(): Boolean? {
        return getValue(Boolean::class.java, Key.signed, false) as Boolean
    }

    override fun setSignedIn(signedIn: Boolean) {
        setValue(Key.signed, signedIn) }

    override fun getUser(): User? {
        return Gson()
            .fromJson(getValue(String::class.java, Key.user, "") as String, User::class.java)
    }

    override fun setUser(user: User) {
        setValue(Key.user, user)
    }

    fun remove(key : Key) { mPref!!.edit().remove(key.name).apply() }
    fun clear() = mPref!!.edit().clear().commit()

    @Suppress("UNCHECKED_CAST")
    fun setValue(
        key: Key,
        value: Any
    ) {
        if (value.javaClass == String::class.java) {
            mPref!!.edit().putString(key.name, value as String).apply()
        } else if (value.javaClass == Int::class.java) {
            mPref!!.edit().putInt(key.name, (value as Int)).apply()
        } else if (value.javaClass == Float::class.java) {
            mPref!!.edit().putFloat(key.name, (value as Float)).apply()
        } else if (value.javaClass == Boolean::class.java) {
            mPref!!.edit().putBoolean(key.name, (value as Boolean)).apply()
        } else if (value.javaClass == Long::class.java) {
            mPref!!.edit().putLong(key.name, (value as Long)).apply()
        } else if (value.javaClass == object : TypeToken<Set<String?>?>() {}.javaClass) {
            mPref!!.edit().putStringSet(key.name, value as Set<String?>).apply()
        } else {
            assert(true)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(
        refClass: Class<T>,
        key: Key,
        defaultValue: Any?
    ): Any? {
        return if (refClass == String::class.java) {
            mPref!!.getString(key.name, defaultValue as String?)
        } else if (refClass == Int::class.java) {
            mPref!!.getInt(key.name, (defaultValue as Int?)!!)
        } else if (refClass == Float::class.java) {
            mPref!!.getFloat(key.name, (defaultValue as Float?)!!)
        } else if (refClass == Boolean::class.java) {
            mPref!!.getBoolean(key.name, (defaultValue as Boolean?)!!)
        } else if (refClass == Long::class.java) {
            mPref!!.getLong(key.name, (defaultValue as Long?)!!)
        } else if (refClass == object :
                TypeToken<Set<String?>?>() {}.javaClass) { mPref!!.getStringSet(key.name, defaultValue as Set<String?>?)
        } else {
            assert(true)
            null
        }
    }

    constructor(context : Context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        const val PREF_NAME = BuildConfig.APPLICATION_ID + ".local"
        var mInstance : PreferenceManager? = null
        var mPref : SharedPreferences? = null

        @Synchronized
        fun getInstance(context: Context): PreferenceManager {
            if (mInstance == null) {
                mInstance = PreferenceManager(context)
            }
            return mInstance!!
        }
    }

    enum class Key {
        accessToken, signed, user
    }
}