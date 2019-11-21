package com.sideproject.manlihyang.side.contents.remote.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.side.contents.model.UserRequest
import java.io.Serializable

// "id": 12,
//  "name": "Iron",
//  "phone": "010xxxxxxxx",
//  "profilePath": null,
//  "email": "kirin@weplanet.co.kr"

data class User(

    @SerializedName("id")
    @Expose
    val id: Long = 0L,
    @SerializedName("name")
    @Expose
    private var _name : String = "",
    @SerializedName("phone")
    @Expose
    private var _phone : String = "",
    @SerializedName("profilePath")
    @Expose
    private var _profilePath : String? = "",
    @SerializedName("email")
    @Expose
    private var _email : String = ""
) : BaseObservable(), Serializable {
    @get:Bindable
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
    }
}