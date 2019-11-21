package com.sideproject.manlihyang.side.contents.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("name")
    @Expose
    val name : String = "",
    @SerializedName("profilePath")
    @Expose
    val profilePath : String? = null
)