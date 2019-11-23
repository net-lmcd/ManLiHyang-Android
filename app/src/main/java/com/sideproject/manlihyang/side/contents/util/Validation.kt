package com.sideproject.manlihyang.side.contents.util

import android.text.TextUtils
import java.util.regex.Pattern

class Validation {

    companion object {

        fun isValidOrNot(input : String, tag : String) : Boolean {
            var result = false
            if (TextUtils.isEmpty(input)) { return false }
            val validator : String = when(tag) {
                CheckType.Email.tag -> "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"
                CheckType.Name.tag -> ""
                CheckType.Password.tag -> "^[0-9a-zA-Z!@#\$%^&*()?+-_~=/]{6,40}\$"
                CheckType.PhoneNumber.tag -> "^01[016789][0-9]{3,4}[0-9]{4}$"
                CheckType.Error.tag -> ""
                else -> ""
            }

            if(validator == "") {
                return false
            } else {
                val pattern = Pattern.compile(validator)
                val matcher = pattern.matcher(input)
                if (matcher.matches()) { result = true }
            }
            return result
        }
    }

    enum class CheckType(var tag : String) {
        Email("email"),
        Name("name"),
        Password("password"),
        PhoneNumber("phonenumber"),
        Error("error")
    }
}