package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An ItemInstance can be an actual item, like a bag of potatoes.
 * An ItemInstanceProperty defines the amount of the content of the bag.
 * An ItemInstance links to an Item that can be a "Potato".
 * Recipes will link to an Item, never an ItemInstance, as user create the instances.
 *
 * @property item The id to the Item this ItemInstance links to
 * @property name Friendly name of the item for the user
 * @property bestBefore Date when the item is about to go bad, in unix timestamp
 */
@Entity
class ItemInstance(
    @PrimaryKey val id: Int,
    var item: Int,
    var name: String,
    var bestBefore: Long? = null
)