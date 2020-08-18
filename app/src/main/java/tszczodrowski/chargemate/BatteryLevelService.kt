package tszczodrowski.chargemate

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class BatteryLevelService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null // not needed
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null)
            return START_STICKY

        when (intent.action) {
            ServiceAction.START.toString() -> start()
            ServiceAction.STOP.toString() -> stop()
        }

        return START_STICKY
    }

    private fun start() {
        toast("BatteryLevelService started")
        // TODO
    }

    private fun stop() {
        toast("BatteryLevelService stopped")
        // TODO
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}