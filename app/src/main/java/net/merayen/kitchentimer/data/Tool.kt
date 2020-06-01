package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a tool in the kitchen, like a induction zone and its power.
 * It also represents the chef itself, and the kitchen.
 * The tool "kitchen" has many other tools linked to it (the equipment).
 * @property name The name of the tool/chef/kitchen
 */
@Entity
data class Tool(
	@PrimaryKey val id: Int,
	val name: String
)