package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A property on a tool.
 *
 * The property can be how long it takes to heat 1 gram of water from 8C to 100C for example.
 *
 * @property type What the property is. E.g: "Bring to boil"
 * @property value The value. Could be gram, liter etc
 */
@Entity(
	foreignKeys = [
		ForeignKey(
			entity = Tool::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("tool")
		)
	]
)
data class ToolProperty(
	@PrimaryKey val id: Int,
	val tool: Int,
	val type: String,
	val value: Float
)