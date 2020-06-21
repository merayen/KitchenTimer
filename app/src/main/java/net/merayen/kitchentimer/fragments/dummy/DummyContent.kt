package net.merayen.kitchentimer.fragments.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, DummyItem> = HashMap()

    init {
        addItem(DummyItem(1, "Skrelle poteter"))
        addItem(DummyItem(2, "Koke opp vann"))
        addItem(DummyItem(3, "Koke poteter"))
        addItem(DummyItem(4, "Steke fisk"))
        addItem(DummyItem(5, "Klargjøre brokoli"))
        addItem(DummyItem(6, "Varme opp flatgrill"))
        addItem(DummyItem(7, "Flambere gulerøtter"))
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: Int, val content: String) {
        override fun toString(): String = content
    }
}
