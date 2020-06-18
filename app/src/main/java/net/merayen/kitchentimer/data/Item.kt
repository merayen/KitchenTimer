package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id: Int,
    var parent: Int,
    var name: String
)
