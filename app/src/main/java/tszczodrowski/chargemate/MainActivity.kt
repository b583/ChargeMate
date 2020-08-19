package tszczodrowski.chargemate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start battery level service
        startForegroundService(Intent(this, BatteryLevelService::class.java))

        // to stop service
//        stopService(Intent(this, BatteryLevelService::class.java))
    }
}