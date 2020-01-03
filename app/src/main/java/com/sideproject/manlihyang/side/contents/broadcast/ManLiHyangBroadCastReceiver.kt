package com.sideproject.manlihyang.side.contents.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ManLiHyangBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "전원 연결", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "전원 해제", Toast.LENGTH_SHORT).show();
        }
    }
}
