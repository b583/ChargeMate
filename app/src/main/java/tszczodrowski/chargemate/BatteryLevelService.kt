package tszczodrowski.chargemate

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BatteryLevelService : Service() {

    private val tag = Common().getTag()
    private var isServiceStarted = false

    override fun onBind(p0: Intent?): IBinder? {
        return null // not needed
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isServiceStarted) {
            Log.w(tag, "BatteryLevelService is already running")
            return START_STICKY
        }

        Log.i(tag, "Starting BatteryLevelService...")
        // TODO start battery checking thread
        isServiceStarted = true
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i(tag, "Stopping BatteryLevelService...")
        super.onDestroy()
        stopForeground(true)
        stopSelf()
        // TODO stop battery checking thread
        Log.i(tag, "BatteryLevelService stopped")
    }

}