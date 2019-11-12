package com.example.sideproject.side.contents.model

import com.example.sideproject.side.contents.remote.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthResponse {

    @SerializedName("accessToken")
    @Expose
    var accessToken : String? = null

    @SerializedName("refreshToken")
    @Expose
    var refreshToken : String? = null

    @SerializedName("user")
    @Expose
    var user : User? = null
}