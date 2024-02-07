package de.pacheco.froggame.feature.catchfrog.ui

import de.pacheco.froggame.core.ressources.R.string.columns
import de.pacheco.froggame.core.ressources.R.string.rows
import de.pacheco.froggame.core.ressources.R.string.startGameNow

enum class Function(val text: Int) {
    STARTGAME(startGameNow)
}

enum class Parameter(val text: Int) {
    ROWS(rows),
    COLS(columns)
}