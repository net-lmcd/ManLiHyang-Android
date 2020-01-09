package com.sideproject.manlihyang.side.contents.data.remote

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.side.contents.model.UserRequest
import com.sideproject.manlihyang.side.contents.model.main.Follow
import java.io.Serializable
import java.sql.Timestamp

// "id": 124,
//  "name": "Iron",
//  "phone": "010xxxxxxxx",
//  "profilePath": null,
//  "email": "cchun0602@gmail.com"

data class User(

    @SerializedName("id")
    @Expose val id: Int = 0,

    @SerializedName("usn")
    @Expose private var usn: String = "",

    @SerializedName("name")
    @Expose var name: String = "",

    @SerializedName("email")
    @Expose private var email: String? = "",

    @SerializedName("notice")
    @Expose private var notice: Boolean = false,

    @SerializedName("notice_chat")
    @Expose private var notice_chat: Boolean = false,

    @SerializedName("profile_url")
    @Expose var profile_url: String? = null,

    @SerializedName("is_blocked")
    @Expose private var is_blocked: Boolean = false,

    @SerializedName("report_cnt")
    @Expose private var report_cnt: Int = 0,

    @SerializedName("follower")
    @Expose var follower: List<Follow> = arrayListOf(),

    @SerializedName("following")
    @Expose var following: List<Follow> = arrayListOf()
) : BaseObservable(), Serializable {
 /*   @get:Bindable
    var name: String
        get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var phone: String
        get() = _phone
        set(value) {
            _phone = value
            notifyPropertyChanged(BR.phone)
        }

    @get:Bindable
    var profilePath: String?
        get() = _profilePath
        set(value) {
            _profilePath = value
            notifyPropertyChanged(BR.profilePath)
        }

    @get:Bindable
    var email: String
        get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    fun toRequest(): UserRequest {
        return UserRequest(
            profilePath = _profilePath,
            name = _name
        )
    }*/
}