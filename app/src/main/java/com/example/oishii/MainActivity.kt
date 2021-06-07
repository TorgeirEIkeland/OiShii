package com.example.oishii

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oishii.Notification.OishiiNotifications

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: OishiiNotifications

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = OishiiNotifications(this)

    }
}