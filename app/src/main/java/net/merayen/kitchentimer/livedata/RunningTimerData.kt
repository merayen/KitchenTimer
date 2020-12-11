package net.merayen.kitchentimer.livedata

data class RunningTimerData(
    val runningTimerId: Int,
    val runningTimerName: String,
    var runningTimerStart: Long,
    var runningTimerElapsed: Int,
    val runningTimerSeconds: Int,
    val taskId: Int?,
    val taskName: String?
) {
    val remaining: Int
        get() = if (runningTimerStart > 0)
            (runningTimerSeconds - (System.currentTimeMillis() / 1000 - runningTimerStart + runningTimerElapsed)).toInt()
        else
            runningTimerSeconds - runningTimerElapsed

    val progress: Float
        get() = if (runningTimerStart > 0)
            (runningTimerElapsed + System.currentTimeMillis() / 1000 - runningTimerStart) / runningTimerSeconds.toFloat()
        else
            runningTimerElapsed / runningTimerSeconds.toFloat()
}