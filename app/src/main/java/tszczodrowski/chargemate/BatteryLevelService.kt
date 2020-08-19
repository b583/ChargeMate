package tszczodrowski.chargemate

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BatteryLevelService : Service() {

    private val tag = Common().getTag()
    private var isServiceStarted = false
    private lateinit var checkingThread: BatteryLevelCheckingThread
    private lateinit var notificationProvider: NotificationProvider

    override fun onBind(p0: Intent?): IBinder? {
        return null // not needed
    }

    private fun initDependencies() {
        checkingThread = BatteryLevelCheckingThread(applicationContext)
        notificationProvider = NotificationProvider(applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isServiceStarted) {
            Log.w(tag, "BatteryLevelService is already running")
            return START_STICKY
        }

        Log.i(tag, "Starting BatteryLevelService...")
        initDependencies()
        startForeground(1, notificationProvider.createNotification())
        checkingThread.start()
        isServiceStarted = true
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i(tag, "Stopping BatteryLevelService...")
        checkingThread.shutdown()
        stopForeground(true)
        stopSelf()
        super.onDestroy()
        Log.i(tag, "BatteryLevelService stopped")
    }

}