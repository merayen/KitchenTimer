package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * References tools to each other.
 * E.g, the "kitchen" tool, the kitchen itself, has many tools like a potato peeler, induction cooker and so on.
 * The chef is also a tool.
 *
 * When cooking, the user selects the kitchen and all the available equipment is then chosen.
 *
 * @property tool The tool that links (e.g kitchen)
 * @property has The tool that the tool links to (e.g potato peeler)
 */
@Entity(
	foreignKeys = [
		ForeignKey(
			entity = Tool::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("tool")
		),
		ForeignKey(
			entity = Tool::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("has")
		)
	]
)
data class ToolReference(
	@PrimaryKey val id: Int,
	val tool: Int,
	val has: Int
)