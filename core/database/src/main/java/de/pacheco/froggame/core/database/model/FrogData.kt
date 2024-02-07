package de.pacheco.froggame.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FrogData(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
