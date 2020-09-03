package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Puts an [ItemInstance] into a [StorageLocation].
 */
@Entity(
    foreignKeys = [
        ForeignKey(entity = ItemInstance::class, parentColumns = arrayOf("id"), childColumns = arrayOf("itemInstance")),
        ForeignKey(entity = ItemInstance::class, parentColumns = arrayOf("id"), childColumns = arrayOf("location"))
    ]
)
class ItemInstanceLocation(
    @PrimaryKey val id: Int,
    var itemInstance: Int,
    var location: Int
)