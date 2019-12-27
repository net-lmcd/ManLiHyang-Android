package com.sideproject.manlihyang.side.contents.util

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sideproject.manlihyang.databinding.MessageDialogFragmentBinding
import com.sideproject.manlihyang.side.contents.base.BaseDialog
import com.sideproject.manlihyang.side.contents.util.extension.isBlankString

class MessageDialogFragment : BaseDialog() {
    private var binding: MessageDialogFragmentBinding? = null
    private var messageDialogClickListener: MessageDialogClickListener? = null

    fun setMessageDialogClickListener(messageDialogClickListener: MessageDialogClickListener) {
        this.messageDialogClickListener = messageDialogClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MessageDialogFragmentBinding.inflate(inflater, container, false)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding!!.root
    }

    override fun isDialogShowFull(): Boolean {
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args == null)
            return
        else {
            if (args.containsKey("title")) {
                val title = args.getString("title")
                binding!!.messageText.setText(title?.isBlankString())
            }
            if (args.containsKey("subTitle")) {
                val subTitle = args.getString("subTitle")
                binding!!.secondaryMessageText.setText(subTitle?.isBlankString())
            } else {
                binding!!.secondaryMessageText.visibility = View.GONE
            }
            if (args.containsKey("positiveTitle")) {
                val positiveTitle = args.getString("positiveTitle")
                if (!args.containsKey("negativeTitle")) {
                    if (args.containsKey("negativeColor")) {
                        val negativeColor = args.getInt("negativeColor")
                        binding!!.confirm.setTextColor(negativeColor)
                    }
                } else if (args.containsKey("positiveColor")) {
                    val positiveColor = args.getInt("positiveColor")
                    binding!!.confirm.setTextColor(positiveColor)
                }
                binding!!.confirm.text = positiveTitle
            } else {
                binding!!.confirm.visibility = View.GONE
            }
            if (args.containsKey("negativeTitle")) {
                val negativeTitle = args.getString("negativeTitle")
                if (args.containsKey("negativeColor")) {
                    val negativeColor = args.getInt("negativeColor")
                    binding!!.cancel.setTextColor(negativeColor)
                }
                binding!!.cancel.text = negativeTitle
            } else {
                binding!!.cancel.visibility = View.GONE
            }
            binding!!.confirm.setOnClickListener {
                dismiss()
                if (messageDialogClickListener != null) {
                    messageDialogClickListener!!.confirmClick()
                }
            }

            binding!!.cancel.setOnClickListener {
                dismiss()
                if (messageDialogClickListener != null) {
                    messageDialogClickListener!!.cancelClick()
                }
            }
            binding!!.executePendingBindings()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (messageDialogClickListener != null) {
            messageDialogClickListener!!.dismiss()
        }
        super.onDismiss(dialog)
    }

    companion object {

        fun instantiate(
            title: String?, subTitle: String?,
            negativeTitle: String?, positiveTitle: String?,
            negativeColor: Int, positiveColor: Int
        ): MessageDialogFragment {
            val dialogFragment = MessageDialogFragment()
            val bundle = Bundle()
            bundle.putInt("negativeColor", negativeColor)
            bundle.putInt("positiveColor", positiveColor)
            if (title != null) bundle.putString("title", title)
            if (subTitle != null) bundle.putString("subTitle", subTitle)
            if (negativeTitle != null) bundle.putString("negativeTitle", negativeTitle)
            if (positiveTitle != null) bundle.putString("positiveTitle", positiveTitle)
            dialogFragment.arguments = bundle
            return dialogFragment
        }
    }

}
