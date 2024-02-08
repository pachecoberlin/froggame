package de.pacheco.froggame.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatchFrog(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,
    val amount: Int,
    val highscore: Int,
)