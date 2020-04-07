package com.sideproject.manlihyang.util.extension

import com.sideproject.manlihyang.util.Validation

fun String.isBlankString() : String{
    if(this.isEmpty()) return ""
    else return this
}

fun String.validate(validator: Validation): Boolean{
    return validator.validate(this)
}