package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Links tools to a task that is required to do the task.
 *
 * @property task The id to the task that requires a tool
 * @property tool The id to the tool that is required
 */
@Entity(
	foreignKeys = [
		ForeignKey(
			entity = Task::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("task")
		),
		ForeignKey(
			entity = Tool::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("tool")
		)
	]
)
data class ToolsRequired(
	@PrimaryKey val id: Int,
	val task: Int,
	val tool: Int
)