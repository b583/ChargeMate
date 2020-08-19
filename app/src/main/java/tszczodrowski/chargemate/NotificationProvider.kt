package tszczodrowski.chargemate

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class NotificationProvider(private val context: Context) {

    private val channelName = "channel-chargeMate"

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = "ChargeMate notifications"
        val importance = NotificationManager.IMPORTANCE_MIN
        val channel = NotificationChannel(channelName, name, importance).apply {
            description = "Notifications used by ChargeMate"
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(): Notification {
        return Notification.Builder(context, channelName)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("ChargeMate is running")
            .setContentText("This notification prevents ChargeMate from closing")
            .setOngoing(true) // prevent user from removing notification
            .setLocalOnly(true) // don't bridge to remote display
            .setVisibility(Notification.VISIBILITY_SECRET) // don't show on lock screen
            .build()
    }

}

