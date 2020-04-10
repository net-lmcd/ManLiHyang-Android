package com.sideproject.manlihyang.util.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sideproject.manlihyang.R
import kotlinx.android.synthetic.main.message_dialog_fragment.*

class MessageDialog : DialogFragment() {

    private var listener: MessageDialogClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.message_dialog_fragment, container)
        isCancelable = false
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if(args!=null) {
            messageText.text = args.getString("title")
            secondaryMessageText.text = args.getString("stitle")
            confirm.apply {
                text = args.getString("ptitle")
                setOnClickListener { listener?.confirmClick(); dismiss() } }
            cancel.apply {
                text = args.getString("ntitle")
                when(this.text.isNullOrEmpty()) {
                    true -> cancel.visibility = View.GONE
                    false -> { setOnClickListener { listener?.cancelClick(); dismiss() } }
                }
            }
        }
    }

    fun setMessageDialogClickListener(listener: MessageDialogClickListener) {
        this.listener = listener
    }

    companion object {
        fun instantiate(title: String, stitle: String = "", ntitle: String = "", ptitle: String = ""): MessageDialog {
            return MessageDialog().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("stitle", stitle)
                    putString("ntitle", ntitle)
                    putString("ptitle", ptitle)
                }
            }
        }
    }
}