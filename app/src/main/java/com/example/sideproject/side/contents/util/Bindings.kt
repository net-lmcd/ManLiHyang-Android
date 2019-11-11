package com.example.sideproject.side.contents.util

import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
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
}