package net.merayen.kitchentimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A task represents a task in the kitchen. Like peeling potatoes, a recipe, and so on.
 * Tasks depends on each other.
 * @property id The unique id of this task
 * @property name The user friendly name of the task
 */
@Entity
data class Task(
	@PrimaryKey val id: Int,
	@ColumnInfo val name: String
)
