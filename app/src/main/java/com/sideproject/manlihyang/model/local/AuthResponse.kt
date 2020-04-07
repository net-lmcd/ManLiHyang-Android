package com.sideproject.manlihyang.model.local

import com.sideproject.manlihyang.data.remote.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(

    @SerializedName("accessToken")
    @Expose
    var accessToken: String? = null,

    @SerializedName("refreshToken")
    @Expose
    var refreshToken: String? = null,

    @SerializedName("user")
    @Expose
    var user: User? = null
)