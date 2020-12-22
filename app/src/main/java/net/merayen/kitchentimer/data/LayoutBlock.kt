package net.merayen.kitchentimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Kitchen layout item.
 *
 * Only supports 1 layout for now.
 */
@Entity
data class LayoutBlock(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "layoutBlockItem") val id: Int,
	@ColumnInfo(name = "layoutItemIndex") val index: Int,
	@ColumnInfo(name = "layoutBlockColor") var color: Int = 0,
	@ColumnInfo(name = "layoutBlockText") var text: String = "",
)