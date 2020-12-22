package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.merayen.kitchentimer.data.LayoutBlock

@Dao
interface LayoutBlockDao {
	@Insert fun set(items: List<LayoutBlock>)
	@Query("SELECT * FROM layoutBlock") fun get(): LiveData<List<LayoutBlock>>
}