package com.sideproject.manlihyang.model.main

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("write_id")
    @Expose var write_id : String,
    @SerializedName("title")
    @Expose var title : String,
    @SerializedName("content")
    @Expose var content : String,
    @SerializedName("file")
    @Expose var image : String,
    @SerializedName("group_seq")
    @Expose var group_seq : Int,
    @SerializedName("group_depth")
    @Expose var group_depth : Int
)