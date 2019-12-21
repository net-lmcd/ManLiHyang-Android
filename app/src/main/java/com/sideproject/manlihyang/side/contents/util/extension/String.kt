package com.sideproject.manlihyang.side.contents.util.extension

import com.sideproject.manlihyang.side.contents.util.Validation

fun String.validate(validator: Validation): Boolean{
    return validator.validate(this)
}