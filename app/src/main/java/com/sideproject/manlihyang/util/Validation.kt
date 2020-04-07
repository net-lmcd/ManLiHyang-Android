package com.sideproject.manlihyang.util

import android.util.Patterns
import java.util.regex.Pattern

enum class Validation(val regex: String){
    NAME("^[a-zA-Z가-힣](?!.*?\\\\s{2})[a-zA-Z가-힣 ]{0,28}[a-zA-Z가-힣]$"),
    EMAIL(Patterns.EMAIL_ADDRESS.pattern()),
    PHONE("^01[016789][0-9]{3,4}[0-9]{4}$"),
    PASSWORD("^[0-9a-zA-Z!@#\$%^&*()?+-_~=/]{6,40}\$"),
    OTP_CODE("^[0-9]{6,6}$");

    fun validate(text: String): Boolean{
        return Pattern.matches(this.regex, text)
    }
}