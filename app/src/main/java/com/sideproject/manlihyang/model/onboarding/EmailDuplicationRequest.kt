package com.sideproject.manlihyang.model.onboarding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmailDuplicationRequest(
    @SerializedName("service_code")
    @Expose
    var service_code : Int = 1000,
    @SerializedName("email")
    @Expose
    var email : String = ""
)