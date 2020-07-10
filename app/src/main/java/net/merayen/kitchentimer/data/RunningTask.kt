package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A task that is running, meaning it is shown in the WorkbenchTab, for what the user is currently doing.
 * @property id You know what this is and this is pointless documentation about that property
 * @property task The task that is being run
 * @property start Is set to something else than 0 when the task is running
 * @property elapsed Total time the task has run (add current time minus "start" if "start" is more than 0 to get total)
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("task")
        )
    ]
)
data class RunningTask(
    @PrimaryKey val id: Int,
    val task: Int,
    val start: Long,
    val elapsed: Int
)