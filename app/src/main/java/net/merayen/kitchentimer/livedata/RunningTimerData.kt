package net.merayen.kitchentimer.livedata

data class RunningTimerData(
    val runningTimerId: Int,
    var runningTimerStart: Long,
    var runningTimerElapsed: Int,
    val runningTimerSeconds: Int,
    val taskId: Int?,
    val taskName: String?
) {
    val remaining: Long
        get() = if (runningTimerStart > 0)
            runningTimerSeconds - (System.currentTimeMillis() / 1000 - runningTimerStart + runningTimerElapsed)
        else
            runningTimerSeconds - runningTimerElapsed.toLong()
}