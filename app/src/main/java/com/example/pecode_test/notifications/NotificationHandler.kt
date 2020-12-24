package com.example.pecode_test.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.random.Random

private const val CHANNEL_ID = "id"
private const val CHANNEL_DESCRIPTION = "Test channel"
private const val CHANNEL_NAME = "1"

class NotificationHandler {
    private lateinit var notificationManager: NotificationManager

    fun sendNotification(context: Context, pageNumber: Int) {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle("New notification")
            .setContentText("From fragment #$pageNumber")
            .setAutoCancel(true)

        notificationManager.notify(Random.nextInt(1, 1000), builder.build())
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            mChannel.description = CHANNEL_DESCRIPTION
            notificationManager =
                context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}