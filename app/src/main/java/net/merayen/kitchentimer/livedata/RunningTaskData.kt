package net.merayen.kitchentimer.livedata

import androidx.room.ColumnInfo
import net.merayen.kitchentimer.data.RunningTask
import net.merayen.kitchentimer.data.Task

data class RunningTaskData(
    val runningTaskId: Int,
    val runningTaskStart: Long,
    val runningTaskElapsed: Int,
    val taskId: Int,
    val taskName: String
)