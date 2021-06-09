package com.example.oishii.Notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.oishii.R

lateinit var notificationManager: NotificationManager


class OishiiNotifications(val context: Context) {
    init {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "order placed",
            "Oishii Notification",
            "Order placed notification"
        )
    }


    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager?.createNotificationChannel(channel)
    }

    fun sendNotification(title: String, content: String){
        val notificationID = 420
        val channelID = "order placed"

        val notification = Notification.Builder(context,
            channelID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.oishii_1)
            .setChannelId(channelID)
            .build()

        notificationManager?.notify(notificationID, notification)

    }
}