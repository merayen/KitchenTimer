package net.merayen.kitchentimer.livedata


data class RunningTaskData(
    val runningTaskId: Int,
    val runningTaskStart: Long,
    val runningTaskElapsed: Int,
    val taskId: Int?,
    val taskName: String?
)