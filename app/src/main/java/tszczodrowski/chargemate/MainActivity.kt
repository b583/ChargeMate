package tszczodrowski.chargemate

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // check battery level
        val batteryLevelGetter = BatteryLevelGetter(applicationContext)
        println(batteryLevelGetter.get())
    }
}