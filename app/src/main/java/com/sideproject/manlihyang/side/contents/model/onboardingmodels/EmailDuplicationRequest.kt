package com.sideproject.manlihyang.side.contents.model.onboardingmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sideproject.manlihyang.BR

data class EmailDuplicationRequest(
    @SerializedName("service_code")
    @Expose
    var service_code : Int = 1000,
    @SerializedName("email")
    @Expose
    private var email : String = ""
)