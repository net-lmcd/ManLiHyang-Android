package com.sideproject.manlihyang.side.contents.util

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sideproject.manlihyang.R

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

  /*  @JvmStatic
    @BindingAdapter("textAttrChanged")
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
    @InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
    fun viewgetTextChanged(view : TextView): String  {
        Log.e("asdf",view.text.toString())
        return view.text.toString()
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun viewsetTextChanged(view: TextView, text: String) {
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
    */

    @JvmStatic
    @BindingAdapter(value = ["notblank","validation"], requireAll = true)
    fun setDrawableEnd(view : AppCompatEditText,
                       notblank : LiveData<Boolean>?,
                       validation : LiveData<Boolean>) {
        val image = when{
            notblank?.value ?: return && !(validation.value ?: return) ->
                R.drawable.button_nocheck_red
            notblank.value ?: return && (validation.value ?: return) ->
                R.drawable.button_check_green
            else -> null
        }

        if(image!=null) {
            view.setCompoundDrawablesWithIntrinsicBounds(0,0,image,0)
        } else {
            view.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
        }

    }
}