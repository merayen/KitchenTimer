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
        addItem(DummyItem("Forvarm teppan"))
        addItem(DummyItem("Hent poteter"))
        addItem(DummyItem("Hent torsk"))
        addItem(DummyItem("Hent brokkoli"))
        addItem(DummyItem("Bland ingredienser til saus"))
        addItem(DummyItem("Vask og kutt brokkoli"))
        addItem(DummyItem("Stek fisk"))
        addItem(DummyItem("Stek gulerøtter"))
        addItem(DummyItem("Stek brokkoli"))
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val content: String) {
        override fun toString(): String = content
    }
}
