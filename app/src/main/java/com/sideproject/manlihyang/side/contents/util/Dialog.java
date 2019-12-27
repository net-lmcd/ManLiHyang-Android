package com.sideproject.manlihyang.side.contents.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

public class Dialog {

    public static MessageDialogFragment showMessage(Context context, FragmentManager fragmentManager, final String message) {
        return new Dialog.Builder(context)
                .setTitle(message)
                .setPositiveTitle("확인")
                .show(fragmentManager);
    }

    public static MessageDialogFragment showMessageConfirm(Context context, FragmentManager fragmentManager, final String message) {
        return new Dialog.Builder(context)
                .setTitle(message)
                .setPositiveTitle("확인")
                .setNegativeTitle("취소")
                .show(fragmentManager);
    }

    public static MessageDialogFragment showMessageConfirm(Context context, FragmentManager fragmentManager, final String message, final String message2) {
        return new Dialog.Builder(context)
                .setTitle(message)
                .setSubTitle(message2)
                .setPositiveTitle("확인")
                .setNegativeTitle("취소")
                .show(fragmentManager);
    }

    public static MessageDialogFragment showSelectRequiredDialogMessage(Context context, FragmentManager fragmentManager, String message) {
        return new Dialog.Builder(context)
                .setTitle(message)
                .setPositiveTitle("확인")
                .setNegativeTitle("취소")
                .show(fragmentManager);
    }

    public static MessageDialogFragment showConfirmDialogMessage(Context context, FragmentManager fragmentManager, String message, String positiveBtnText) {
        return new Dialog.Builder(context)
                .setTitle(message)
                .setNegativeTitle("취소")
                .setPositiveTitle(positiveBtnText)
                .show(fragmentManager);
    }

    public static class Builder{
        @NonNull
        private Context context;
        private int negativeColor;
        private int positiveColor;
        @Nullable
        private String title = null;
        @Nullable
        private String subTitle = null;
        @Nullable
        private String negativeTitle = null;
        @Nullable
        private String positiveTitle = null;
        @Nullable
        private MessageDialogClickListener listener = null;

        public Builder(@NonNull Context context){
            this.context = context;
            this.negativeColor = Color.parseColor("#e5002c");
            this.positiveColor = Color.parseColor("#7304040f");
        }

        public Builder setListener(@Nullable MessageDialogClickListener listener){
            this.listener = listener;
            return this;
        }

        public Builder setTitle(@StringRes int resId){
            this.setTitle(context.getString(resId));
            return this;
        }

        public Builder setSubTitle(@StringRes int resId) {
            this.setSubTitle(context.getString(resId));
            return this;
        }

        public Builder setTitle(@Nullable String title) {
            this.title = title;
            return this;
        }

        public Builder setSubTitle(@Nullable String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public Builder setNegativeTitle(@StringRes int resId) {
            this.setNegativeTitle(context.getString(resId));
            return this;
        }

        public Builder setNegativeTitle(@Nullable String negativeTitle) {
            this.negativeTitle = negativeTitle;
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public Builder setNegativeColor(@ColorRes int resId) {
            this.negativeColor = context.getColor(resId);
            return this;
        }

        @TargetApi(Build.VERSION_CODES.M)
        public Builder setPositiveColor(@ColorRes int positiveColor) {
            this.positiveColor = context.getColor(positiveColor);
            return this;
        }

        public Builder setPositiveTitle(@StringRes int positiveTitle) {
            this.setPositiveTitle(context.getString(positiveTitle));
            return this;
        }

        public Builder setPositiveTitle(@Nullable String positiveTitle) {
            this.positiveTitle = positiveTitle;
            return this;
        }

        public MessageDialogFragment build(){
            final MessageDialogFragment dialogFragment =  MessageDialogFragment.Companion.instantiate(
                    title, subTitle,
                    negativeTitle, positiveTitle,
                    negativeColor, positiveColor
            );
            dialogFragment.setMessageDialogClickListener(listener);
            return dialogFragment;
        }

        public MessageDialogFragment show(@NonNull FragmentManager fragmentManager){
            final MessageDialogFragment dialogFragment = build();
            dialogFragment.show(fragmentManager, "MessageDialogFragment");
            return dialogFragment;
        }
    }
}
