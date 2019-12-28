package com.sideproject.manlihyang.side.contents.remote.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.side.contents.model.UserRequest
import java.io.Serializable
import java.sql.Timestamp

// "id": 12,
//  "name": "Iron",
//  "phone": "010xxxxxxxx",
//  "profilePath": null,
//  "email": "kirin@weplanet.co.kr"

data class User(

    @SerializedName("id")
    @Expose
    val id: Int = 0,
    @SerializedName("usn")
    @Expose
    private var _name : String = "",
    @SerializedName("username")
    @Expose
    private var username : String = "",
    @SerializedName("email")
    @Expose
    private var email : String? = "",
    @SerializedName("password")
    @Expose
    private var password : String = "",
    @SerializedName("password")
    @Expose
    private var created_at : Timestamp = Timestamp(0),
    @SerializedName("notice")
    @Expose
    private var notice : Boolean = false,
    @SerializedName("notice_chat")
    @Expose
    private var notice_chat : Boolean = false,
    @SerializedName("profile_url")
    @Expose
    private var profile_url : String? = null,
    @SerializedName("is_blocked")
    @Expose
    private var is_blocked : Boolean = false,
    @SerializedName("report_cnt")
    @Expose
    private var report_cnt : Int = 0
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