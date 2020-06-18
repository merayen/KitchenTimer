package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Property on an instance of an item.
 */
@Entity
class ItemInstanceProperty(
    @PrimaryKey val id: Int,
    var name: String,
    var value: String
)