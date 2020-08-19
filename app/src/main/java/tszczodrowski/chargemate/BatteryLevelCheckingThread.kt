package tszczodrowski.chargemate

import android.content.Context

class BatteryLevelCheckingThread(context: Context) : Thread() {

    private val batteryLevelGetter = BatteryLevelGetter(context)

    // TODO parametrize
    private val highThreshold: Int = 80
    private val lowThreshold: Int = 40

    private var isShutdown: Boolean = false
    // TODO calculate value between thresholds?
    // 60 is good because it works for both increase and decrease case (if next reads are valid)
    private var lastBatteryLevel: Int = batteryLevelGetter.get() ?: 60

    override fun run() {
        while(!isShutdown) {
            runCycle()
            sleep()
        }
    }

    private fun runCycle() {
        val newBatteryLevel: Int = batteryLevelGetter.get() ?: return

        if (hasIncreased(newBatteryLevel)) {
            onIncreased(newBatteryLevel)
        } else if (hasDecreased(newBatteryLevel)) {
            onDecreased(newBatteryLevel)
        }

        lastBatteryLevel = newBatteryLevel
    }

    private fun hasIncreased(newBatteryLevel: Int): Boolean {
        return newBatteryLevel > lastBatteryLevel
    }

    private fun hasDecreased(newBatteryLevel: Int): Boolean {
        return newBatteryLevel < lastBatteryLevel
    }

    private fun onIncreased(newBatteryLevel: Int) {
        if(newBatteryLevel < highThreshold) {
            return
        }

        println("breakpoint placeholder")
        // TODO some kind of alarm
    }

    private fun onDecreased(newBatteryLevel: Int) {
        if(newBatteryLevel > lowThreshold) {
            return
        }

        println("breakpoint placeholder")
        // TODO some kind of alarm
    }

    private fun sleep() {
        sleep(1000L)
    }

    fun shutdown() {
        isShutdown = true
    }

}