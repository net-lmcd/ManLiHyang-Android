package com.sideproject.manlihyang.model.onboarding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmailDuplicationResponse(
    @SerializedName("timestamp")
    @Expose var timestamp : String,

    @SerializedName("service_code")
    @Expose var service_code : Int,

    @SerializedName("message")
    @Expose var message : String
)