package com.sideproject.manlihyang.side.contents.util;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sideproject.manlihyang.databinding.MessageDialogFragmentBinding;
import com.sideproject.manlihyang.side.contents.base.BaseDialog;

/**
 * Created by bek on 01/08/2017.
 */

public class MessageDialogFragment extends BaseDialog {
    private MessageDialogFragmentBinding binding;
    private MessageDialogClickListener messageDialogClickListener;

    public static MessageDialogFragment instantiate(
            @Nullable String title, @Nullable String subTitle,
            @Nullable String negativeTitle, @Nullable String positiveTitle,
            int negativeColor, int positiveColor) {
        final MessageDialogFragment dialogFragment = new MessageDialogFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt("negativeColor", negativeColor);
        bundle.putInt("positiveColor", positiveColor);
        if(title != null) bundle.putString("title", title);
        if(subTitle != null) bundle.putString("subTitle", subTitle);
        if(negativeTitle != null) bundle.putString("negativeTitle", negativeTitle);
        if(positiveTitle != null) bundle.putString("positiveTitle", positiveTitle);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    public void setMessageDialogClickListener(MessageDialogClickListener messageDialogClickListener) {
        this.messageDialogClickListener = messageDialogClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MessageDialogFragmentBinding.inflate(inflater, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return binding.getRoot();
    }

    @Override
    public boolean isDialogShowFull() {
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle args = getArguments();
        if (args == null) return;
        else {
            if (args.containsKey("title")) {
                final String title = args.getString("title");
                binding.messageText.setText(dataCheck(title));
            }
            if (args.containsKey("subTitle")) {
                final String subTitle = args.getString("subTitle");
                binding.secondaryMessageText.setText(dataCheck(subTitle));
            } else {
                binding.secondaryMessageText.setVisibility(View.GONE);
            }
            if (args.containsKey("positiveTitle")) {
                final String positiveTitle = args.getString("positiveTitle");
                if(!args.containsKey("negativeTitle")){
                    if (args.containsKey("negativeColor")) {
                        final int negativeColor = args.getInt("negativeColor");
                        binding.confirm.setTextColor(negativeColor);
                    }
                }else if(args.containsKey("positiveColor")) {
                    final int positiveColor = args.getInt("positiveColor");
                    binding.confirm.setTextColor(positiveColor);
                }
                binding.confirm.setText(positiveTitle);
            } else {
                binding.confirm.setVisibility(View.GONE);
            }
            if (args.containsKey("negativeTitle")) {
                final String negativeTitle = args.getString("negativeTitle");
                if (args.containsKey("negativeColor")) {
                    final int negativeColor = args.getInt("negativeColor");
                    binding.cancel.setTextColor(negativeColor);
                }
                binding.cancel.setText(negativeTitle);
            } else {
                binding.cancel.setVisibility(View.GONE);
            }
            binding.confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (messageDialogClickListener != null) {
                        messageDialogClickListener.confirmClick();
                    }
                }
            });

            binding.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (messageDialogClickListener != null) {
                        messageDialogClickListener.cancelClick();
                    }
                }
            });
            binding.executePendingBindings();
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        if (messageDialogClickListener != null) {
            messageDialogClickListener.dismiss();
        }
        super.onDismiss(dialog);
    }

    public static String dataCheck(Object s) {
        if (s == null) {
            return "";
        }
        if ((s instanceof String) && (((String) s).trim().length() == 0)) {
            return "";
        }
        return String.valueOf(s);
    }

}
