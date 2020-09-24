package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parent")
        )
    ]
)
data class Location(
    @PrimaryKey override val id: Int,
    override val parent: Int? = null,
    override val name: String
) : TreeItem, NamedItem