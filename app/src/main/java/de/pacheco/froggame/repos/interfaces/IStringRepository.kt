package de.pacheco.froggame.repos.interfaces

interface IStringRepository {
    fun getString(resID: Int): String

    val game: String
    val load: String
    val mainMenu: String
    val newGame: String
    val options: String
    val save: String
    val saveLoad: String
    val skip: String
    val startCatchFrogs: String
}