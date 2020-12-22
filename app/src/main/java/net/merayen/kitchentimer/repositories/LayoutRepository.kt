package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.dao.LayoutBlockDao
import net.merayen.kitchentimer.data.LayoutBlock
import javax.inject.Inject

class LayoutRepository @Inject constructor(
	private val layoutBlockDao: LayoutBlockDao
) {
	fun set(layoutBlocks: List<LayoutBlock>) = layoutBlockDao.set(layoutBlocks)
	fun set(layoutBlock: LayoutBlock) = layoutBlockDao.set(layoutBlock)
	fun get() = layoutBlockDao.get()
}