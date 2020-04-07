package com.sideproject.manlihyang.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.ui.activity.SplashActivity

class ManLiHyangMessagingService : FirebaseMessagingService() {

    private val mNotificationId : Int
        get() = SystemClock.uptimeMillis().toInt()
    private val mNotificationManager : NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(this)
    }

    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(NotificationChannel(
                CHANNEL_ID,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = getString(R.string.channel_description)
                canShowBadge()
                setShowBadge(true)
                enableLights(true)
                enableVibration(true)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    @Suppress("DEPRECATION")
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.e("remote", p0.notification.toString())

        val notification = p0.notification ?: return
        val notificationBuilder =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                NotificationCompat.Builder(this, CHANNEL_ID)
            else
                NotificationCompat.Builder(this)

        val intent = Intent(this, SplashActivity::class.java)

        //사용자가 알림을 클릭했을때 동작을 정의함
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        mNotificationManager.notify(
            mNotificationId,
            notificationBuilder
                .setContentTitle(notification.title)
                .setContentText(notification.body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setColorized(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .build()
        )
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
    }
}