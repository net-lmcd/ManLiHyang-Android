package com.sideproject.manlihyang.side.contents.model.onboardingmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmailDuplicationCheck(
    @SerializedName("service_code")
    @Expose var service_code : Int,
    @SerializedName("email")
    @Expose var email : String
)