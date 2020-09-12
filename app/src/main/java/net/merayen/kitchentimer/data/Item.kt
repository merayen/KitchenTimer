package net.merayen.kitchentimer.data

import androidx.room.Entity
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
@Entity
data class Item(
    @PrimaryKey override val id: Int,
    override var parent: Int,
    override var name: String
) : NamedItem, TreeItem