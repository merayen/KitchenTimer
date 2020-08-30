package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Property on an instance of an item.
 */
@Entity
data class ItemInstanceProperty(
    @PrimaryKey val id: Int,
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