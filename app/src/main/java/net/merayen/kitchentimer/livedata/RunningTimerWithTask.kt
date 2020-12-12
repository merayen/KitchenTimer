package net.merayen.kitchentimer.livedata

import androidx.room.Embedded
import androidx.room.Relation
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.data.Task

data class RunningTimerWithTask(
    @Embedded
    val runningTimer: RunningTimer,

    @Relation(parentColumn = "task", entityColumn = "id")
    val task: Task?
)
