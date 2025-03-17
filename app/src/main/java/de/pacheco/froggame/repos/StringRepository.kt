package de.pacheco.froggame.repos

import android.content.Context
import de.pacheco.froggame.core.ressources.R.string
import de.pacheco.froggame.repos.interfaces.IStringRepository
import javax.inject.Inject

class StringRepository @Inject constructor(private val context: Context) : IStringRepository {
    override fun getString(resID: Int): String {
        return context.getString(resID)
    }

    override val newGame: String = getString(string.newGame)
    override val mainMenu: String = getString(string.mainMenu)
    override val saveLoad: String = getString(string.saveLoad)
    override val save: String = getString(string.save)
    override val load: String = getString(string.load)
    override val options: String = getString(string.options)
    override val startCatchFrogs: String = getString(string.startCatchFrogs)
}
