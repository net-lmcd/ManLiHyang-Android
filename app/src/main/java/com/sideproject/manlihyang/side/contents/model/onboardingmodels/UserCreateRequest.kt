package com.sideproject.manlihyang.side.contents.model.onboardingmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserCreateRequest(
    @SerializedName("username")
    @Expose var username: String,

    @SerializedName("email")
    @Expose var email: String,

    @SerializedName("password")
    @Expose var password: String,

    @SerializedName("notice")
    @Expose var notice : Boolean,

    @SerializedName("notice_chat")
    @Expose var notice_chat : Boolean
)