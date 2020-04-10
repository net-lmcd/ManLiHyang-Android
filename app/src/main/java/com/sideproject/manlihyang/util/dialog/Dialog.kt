package com.sideproject.manlihyang.util.dialog

import android.content.Context
import androidx.fragment.app.FragmentManager

class Dialog {

    companion object {
        fun showMessage(context: Context, fragmentManager: FragmentManager, message: String, submessage: String = "", extra: Boolean = false, listener: MessageDialogClickListener? = null): MessageDialog {
            return Builder(context)
                .setTitle(message)
                .setSubTitle(submessage)
                .setButtons(extra)
                .setListener(listener)
                .show(fragmentManager)
        }
    }

    class Builder(private val context: Context) {
        private var title: String = ""
        private var subTitle: String = ""
        private var negativeTitle: String = ""
        private var positiveTitle: String = ""
        private var listener: MessageDialogClickListener? = null

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setSubTitle(subTitle: String): Builder {
            this.subTitle = subTitle
            return this
        }

        fun setButtons(extra: Boolean): Builder {
            this.positiveTitle = "확인"
            if(extra) this.negativeTitle = "취소"
            return this
        }

        fun setNegativeTitle(negativeTitle: String): Builder {
            this.negativeTitle = negativeTitle
            return this
        }

        fun setPositiveTitle(positiveTitle: String): Builder {
            this.positiveTitle = positiveTitle
            return this
        }

        fun setListener(listener: MessageDialogClickListener?): Builder {
            listener?.let { this.listener = it }
            return this
        }

        fun build(): MessageDialog {
            return MessageDialog.instantiate(title, subTitle, negativeTitle, positiveTitle).apply {
                listener?.let { setMessageDialogClickListener(it) }
            }
        }

        fun show(fragmentManager: FragmentManager): MessageDialog {
            val dialogFragment = build()
            dialogFragment.show(fragmentManager, "MessageDialog")
            return dialogFragment
        }
    }
}