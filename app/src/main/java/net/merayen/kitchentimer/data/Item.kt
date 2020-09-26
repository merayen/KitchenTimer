package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * An [Item] defines types, like:
 * - Potatoes
 * - Tomato
 * - Milk
 * - Skillet
 * - Freezer
 *
 * See [ItemInstance].
 *
 * Recipes links to an [Item] for what is needed. This app figures out then which [ItemInstance] that should be
 * actually used.
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parent")
        )
    ]
)
data class Item(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    override var parent: Int? = null,
    override var name: String
) : NamedItem, TreeItem