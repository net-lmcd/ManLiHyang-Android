package com.sideproject.manlihyang.side.contents.util

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import timber.log.Timber

object Keyboard {

    fun showKeyboard(now : Any?) {
        var imm : InputMethodManager? = null
        when(now) {
            is Activity -> imm = now.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            is Fragment -> imm = now.activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            else -> {
                Timber.e("Error from showKeyboard() -> This is not an Activity or Fragment")
                return
            }
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard(now : Any?) {
        var imm : InputMethodManager? = null
        var view : View? = null
        when(now) {
            is Activity -> {
                imm = now.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                view = now.currentFocus
            }
            is Fragment -> {
                imm = now.activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                if(now.activity != null) {
                    view = now.activity!!.currentFocus
                } else {
                    Timber.e("Error from hideKeyboard() -> This fragment has no Activity")
                    return
                }
            } else -> {
                Timber.e("Error from hideKeyboard() -> This is not an Activity or Fragment")
                return
            }
        }
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun hideKeyboardChildAswell(view : View, now: Any?) {

        when(view) {
            is ViewGroup -> {
                for(pos in 0 until view.childCount) {
                    val childView = view.getChildAt(pos)
                    hideKeyboardChildAswell(childView, now)
                }
            }
            !is EditText -> {
                hideKeyboard(now)
            }
        }
    }
}