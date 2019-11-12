package com.example.sideproject.side.contents.remote.model

import androidx.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// "id": 12,
//  "name": "kirin",
//  "phone": "01014141414",
//  "profilePath": null,
//  "familyId": null,
//  "email": "kirin@weplanet.co.kr",
//  "talkCount": 0,
//  "dogs": [],
//  "invites": []
data class User(
    @SerializedName("id")
    @Expose
    val id: Long = 0L,
    @SerializedName("name")
    @Expose
    private var _name: String = ""
/*    @SerializedName("familyMembers")
    @Expose
    var familyMembers: List<Family> = arrayListOf(),
    @SerializedName("phone")
    @Expose
    private var _phone: String = "",
    @SerializedName("profilePath")
    @Expose
    private var _profilePath: String? = null,
    @SerializedName("familyId")
    @Expose
    private val familyId: Long? = null,
    @SerializedName("email")
    @Expose
    private var _email: String = "",
    @SerializedName("talkCount")
    @Expose
    private val talkCount: Long = 0L,
    @SerializedName("dogs")
    @Expose
    private val _dogs: List<DogResponse> = arrayListOf(),
    @SerializedName("invites")
    @Expose
    val invites: List<Invite> = arrayListOf(),
    @SerializedName("sentInvites")
    @Expose
    val sentInvites: List<Invite> = arrayListOf()*/
) : BaseObservable(), Serializable {
/*    @get:Bindable
    var name: String
        get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    val dogDescription: String
        get() = dogs.joinToString {
            it.name
        } + if (dogs.count() > 2) "외 $${dogs.count() - 2}" else ""

    @get:Bindable
    val dogs: List<Dog>
        get() = _dogs.map{
            it.toDog()
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

    fun toRequest(): UserRequest{
        return UserRequest(
            profilePath = _profilePath,
            name = _name
        )
    }*/
}