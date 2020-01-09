package com.sideproject.manlihyang.side.contents.model.main

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Follow(
    @SerializedName("senderId")
    @Expose var senderId: Int? = null,

    @SerializedName("senderName")
    @Expose var senderName: String? = null,

    @SerializedName("phone")
    @Expose var phone: String? = null,

    @SerializedName("createdAt")
    @Expose var createdAt: String? = null,

    @SerializedName("senderProfilePath")
    @Expose var senderProfilePath: String? = null
) : Serializable {
}