package net.merayen.kitchentimer.livedata


data class RunningTimerData(
    val runningTimerId: Int,
    val runningTimerStart: Long,
    val runningTimerElapsed: Int,
    val taskId: Int?,
    val taskName: String?
)