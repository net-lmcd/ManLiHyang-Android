package com.sideproject.manlihyang.side.contents.util

import android.text.TextUtils
import java.util.regex.Pattern

class Validation {

    companion object {

        fun isValidOrNot(input : String, type : CheckType) : Boolean {
            var result = false
            if (TextUtils.isEmpty(input)) { return false }
            val validator : String = when(type) {
                CheckType.Email -> "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"
                CheckType.Password -> "^[0-9a-zA-Z!@#\$%^&*()?+-_~=/]{6,40}\$"
                CheckType.PhoneNumber -> "^01[016789][0-9]{3,4}[0-9]{4}$"
            }
            val pattern = Pattern.compile(validator)
            val matcher = pattern.matcher(input)
            if (matcher.matches()) { result = true }
            return result
        }
    }

    enum class CheckType(var tag : String) {
        Email("email"),
        Password("password"),
        PhoneNumber("phonenumber")
    }
}