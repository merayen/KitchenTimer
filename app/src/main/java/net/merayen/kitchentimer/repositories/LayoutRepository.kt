package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.dao.LayoutBlockDao
import net.merayen.kitchentimer.data.LayoutBlock
import javax.inject.Inject

class LayoutRepository @Inject constructor(
	private val layoutBlockDao: LayoutBlockDao
) {
	fun set(layoutBlocks: List<LayoutBlock>) = layoutBlockDao.set(layoutBlocks)
	fun get() = layoutBlockDao.get()
}