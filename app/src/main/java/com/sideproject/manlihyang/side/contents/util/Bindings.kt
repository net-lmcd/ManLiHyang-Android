package com.sideproject.manlihyang.side.contents.util

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 *  Bindings for Databinding
 */

object Bindings {

    @JvmStatic
    @BindingAdapter(value = ["string","number"] ,requireAll = false)
    fun bindingSetting(view : BottomNavigationView,
                       string : Int,
                       number : Int) {
        Log.e("BottomNavigationView",  "$string " + number)
    }

    @JvmStatic
    @BindingAdapter("textChanged")
    fun confirmTextViewChanged(view : TextView, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                listener.onChange()
            }
        })
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "input", event = "textChanged")
    fun viewsetTextChanged(view : TextView): String  {

        Log.e("asdf","asdf")
        return ""
    }

    @JvmStatic
    @BindingAdapter("input")
    fun viewgetTextChanged(view: TextView, text: String) {
        var check = Validation.isValidOrNot(text, view.tag as String)
        var result =  when(check) {
            true -> {
                view.setTextColor(Color.BLUE)
                "*올바른 형식입니다."
            }
            false -> {
                view.setTextColor(Color.BLACK)
                "*올바르지 않은 형식입니다."
            }
        }

        view.text = result
    }
}