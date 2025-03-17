package de.pacheco.froggame.repos.interfaces

interface IStringRepository {
    fun getString(resID: Int): String

    val newGame: String
    val mainMenu: String
    val saveLoad: String
    val save: String
    val load: String
    val options: String
    val startCatchFrogs: String
}