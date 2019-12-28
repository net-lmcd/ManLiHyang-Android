package com.sideproject.manlihyang.side.contents.model.onboardingmodels

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