package com.sideproject.manlihyang.side.contents.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.sideproject.manlihyang.R
import kotlinx.android.synthetic.main.dialog_loading.*

class CircularProgress(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}