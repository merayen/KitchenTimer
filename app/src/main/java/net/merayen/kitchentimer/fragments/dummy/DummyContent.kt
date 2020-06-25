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

    init {
        addItem(DummyItem(1, "Forvarm teppan"))
        addItem(DummyItem(2, "Hent poteter"))
        addItem(DummyItem(3, "Hent torsk"))
        addItem(DummyItem(4, "Hent brokkoli"))
        addItem(DummyItem(5, "Bland ingredienser til saus"))
        addItem(DummyItem(6, "Vask og kutt brokkoli"))
        addItem(DummyItem(7, "Stek fisk"))
        addItem(DummyItem(8, "Stek gulerøtter"))
        addItem(DummyItem(9, "Stek brokkoli"))
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: Int, val content: String) {
        override fun toString(): String = content
    }
}
