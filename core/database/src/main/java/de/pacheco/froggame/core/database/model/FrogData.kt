package de.pacheco.froggame.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.pacheco.froggame.core.model.Frog

@Entity
data class FrogData(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

fun FrogData.asExternalModel(): Frog = Frog(id = uid, name = name)
