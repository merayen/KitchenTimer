package net.merayen.kitchentimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A recipe is shown on the menu.
 * @property id You know what this is
 * @property name Name of the recipe, shown in the title
 * @property description A longer description of what
 */
@Entity(
    foreignKeys = [
        ForeignKey(entity = Task::class, parentColumns = arrayOf("id"), childColumns = arrayOf("task"))
    ]
)
data class Recipe(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val task: Int
)