package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Property on an instance of an item.
 */
@Entity(
    foreignKeys = [
        ForeignKey(entity = ItemInstance::class, parentColumns = arrayOf("id"), childColumns = arrayOf("itemInstance"))
    ]
)
data class ItemInstanceProperty(
    @PrimaryKey val id: Int,
    var itemInstance: Int,
    var name: String,
    var value: Float
) {
    enum class Type {
        WEIGHT, // in grams
        VOLUME, // in milliliters
        WIDTH, // in millimeters
        HEIGHT, // in millimeters
        RADIUS // in millimeters
    }
}